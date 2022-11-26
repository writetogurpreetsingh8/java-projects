package com.king.httpserver.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.king.httpserver.core.HTTPSession;

public abstract class SessionUtility {

	/**
	 * Generating random unique session ID by using UUID and replacing - from it.
	 */
	public static String generateSessionKey() {
		// UUID is thread safe internally SecureRandom is being used to create random number
		// which is itself thread safe
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	 /**
     * calculate session validity by adding extra time into it
     * by default session will alive upto 10 mins only so added 10min into it
     */
    public static Instant addTimeIntoCurrentTime(int extraTime, final Instant currentSessionTime) {
    	return currentSessionTime.plus(extraTime, ChronoUnit.MINUTES);
    }
    
    /**
     * checking whether session is valid or not
     */
    public static boolean isSessionValid(final HTTPSession httpSession, final Instant currentTime) {
    	return httpSession.getValidateUpto().isAfter(currentTime);
    }
    
    /**
     * checking whether isDoCleanUPRun() should execute or not
     * it should execute every after 60 mins from its last execution 
     */
    public static boolean isDoCleanUpRun(final Instant doCleanUpTime , final Instant currentTime) {
    	return doCleanUpTime.isBefore(currentTime);
    }
}
