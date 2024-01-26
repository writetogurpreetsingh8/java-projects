package com.king.httpserver.core;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.king.httpserver.config.ConfigurationManager;
import com.king.httpserver.utils.SessionUtility;

/**
 * DataAccessor hold some business logic to manipulate User's HTTPSession
 */
public class DataAccessor {
	
	private Lock readLock;
	private Lock writeLock;

	private DataAccessor() {
		ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
		this.readLock = readWriteLock.readLock();
		this.writeLock = readWriteLock.writeLock();
	}

	// thread safe implementation by static inner class
	private static class DataProcessorSigleton {
		private final static DataAccessor dataAccessor = new DataAccessor();
	}

	public static DataAccessor getInstance() {
		return DataProcessorSigleton.dataAccessor;
	}

	// add user's http-session into http-session Collection named ConcurrentHashMap
	public HTTPSession addNewHTTPSession() {
		
		final HTTPSession session = new HTTPSession();
		session.set_id(SessionUtility.generateSessionKey());
		session.setCreatedOn(Instant.now());
		session.setValidateUpto(SessionUtility.addTimeIntoCurrentTime(
				ConfigurationManager.getInstance().getConfiguration().getSessionValidUptoMins(),
				session.getCreatedOn()));
		HTTPSessionsHolder.getSessions().put(session.get_id(), session);
		return session;
	}

	/**
	 * getting existing http-session by its sessionKey if exist, before getting
	 *  doCleanUp will remove all the obsolete http-session
	 */
	public HTTPSession retrieveHTTPSession(final String sessionKey) {
		return HTTPSessionsHolder.getSessions().get(sessionKey);
	}

	// removing http-session if it got expired after 10 mins
	public void removeExpiredHTTPSession(final String sessionKey) {
		HTTPSessionsHolder.getSessions().remove(sessionKey);
	}

	/**
	  checking whether http-session is still valid or not , logically validity of session is just 10 min
      mention into application.json file
	 */
	public boolean isSessionValid(final HTTPSession httpSession) {
		return SessionUtility.isSessionValid(httpSession,Instant.now());
	}
	 
	/**
	 * if user already has level against given level id then update level with score
       else create new level and update given score into it!! 
	 */
	public void updateUserLevelWithScores(final User user, final Integer levelId, final Integer score) {

		// if user is not new and have some level
		if (user.getLevels() != null && !user.getLevels().isEmpty()) {
			final Level level = user.getLevels().get(levelId); // get level against levelId

			try {
				// check if user has level against given levelId if yes then update level with
				// score
				this.writeLock.lock();
				if (level != null) {
					level.getScores().add(score);
				} else {
					final Level newLevel = createLevelAndUpdateWithScore(levelId, score); // receive new level with its
																							// score

					user.getLevels().put(levelId, newLevel); // put level and its corresponding score into
																// concurrent-hash-map to achieve thread safety
				}
			} finally {
				this.writeLock.unlock();
			}
		} else {
			// user has no level, create new level and set its score for the first time.
			try {
				this.writeLock.lock();
				final Map<Integer, Level> levels = new ConcurrentHashMap<Integer, Level>();
				final Level level = createLevelAndUpdateWithScore(levelId, score); // receive new level with its score
				levels.put(levelId, level); // put level and its corresponding score into concurrent-hash-map to achieve
											// thread safety

				user.setLevels(levels); // set levels into user
			} finally {
				this.writeLock.unlock();
			}
		}
	}

	
	/**
	 * getting highest score against given levelid
	   and returning the Map of Integer,Integer, key is user and value is level's highest score
	 */
	public Map<Integer, Integer> retreiveHighestScore(final Integer levelId) {
		// may be we have some obsolete session so before getting highest max score need
		// to remove obsolete session
		doCleanUp();

		final Collection<HTTPSession> httpSessions = HTTPSessionsHolder.getSessions().values();
		final Iterator<HTTPSession> itr = httpSessions.iterator();
		
		// tree-map to store user as key and value as highest score of each level
		Map<Integer, Integer> highestScoresHolder = new TreeMap<Integer, Integer>();

		int scoreThreshold = ConfigurationManager.getInstance().getConfiguration().getScoreThreshold();
		boolean isGivenLevelExist = false;
		
		while (itr.hasNext()) {
			final HTTPSession session = itr.next();
			final Level level = session.getUser().getLevels().get(levelId);
			if(level != null) {
				int highestScore = findMaximumScoreForEachLevel(level);
				highestScoresHolder.put(session.getUser().getUserId(), highestScore);
				isGivenLevelExist = true;
			}
			else {
				// if score doesn't exist for any level then put -1 to 
				// identify whether user have submitted the score for level or not
				highestScoresHolder.put(session.getUser().getUserId(), -1);
			}
			scoreThreshold--;
			if (scoreThreshold == 0) {
				break;
			}
		}
		// if given level doesn't exist then empty highestScoresHolder TreeMap to generate Empty CSV
		if(!isGivenLevelExist) {
			highestScoresHolder = null;
		}
		return highestScoresHolder;
	}

	/**
	 * this method will remove all the obsolete sessions which are not being used since last it's last
	 * default session values( 10 mins )
	 */
	private void doCleanUp() {
		// removing all obsolete sessions
		Instant currentTime = Instant.now();
		HTTPSessionsHolder.getSessions().entrySet().removeIf(entry -> !SessionUtility.isSessionValid(entry.getValue(),currentTime));
	}
	/**
	 * creating new level and update it with given score
	 */
	private Level createLevelAndUpdateWithScore(final Integer levelId, final int score) {
		final Level level = new Level(); // create new level
		level.set_id(levelId); // set levelId into it
		
		// can't use Synchronized ArrayList because it is block in nature
		// CopyOnWriteArrayList thread-safe but very costly when it comes to write operation
		// So using normal thread-unsafe ArrayList but while updating this list 
		// using Write/Read lock respectively
		List<Integer> scores = new ArrayList<Integer>(); // create new list to hold the score
		scores.add(score); // add score into list
		level.setScores(scores); // set score into level
		return level;
	}
	
	private int findMaximumScoreForEachLevel(final Level level) {
		
		int value = 0;
		try {
			this.readLock.lock();
			// getting lock on underlying ArrayList  
			final Optional<Integer> highestScoreOfLevel = level.getScores().stream()
					.max(Comparator.comparing(Integer::intValue));
			// user and store highest score of level 
			value = highestScoreOfLevel.get();
		}
		finally {
			this.readLock.unlock();
		}
		return value;
	}
}
