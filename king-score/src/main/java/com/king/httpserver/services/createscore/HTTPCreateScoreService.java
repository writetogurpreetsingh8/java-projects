/**
 * 
 */
package com.king.httpserver.services.createscore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.king.httpserver.constants.Constant;
import com.king.httpserver.core.DataAccessor;
import com.king.httpserver.core.HTTPSession;
import com.king.httpserver.request.HTTPRequest;
import com.king.httpserver.response.HTTPResponse;
import com.king.httpserver.services.AbstractHTTPService;
import com.king.httpserver.services.filter.HTTPStatusCode;

public class HTTPCreateScoreService extends AbstractHTTPService {

	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPCreateScoreService.class);

	// thread safe implementation by static inner class
	private static class HTTPCreateScoreServiceSingleton {
		private final static HTTPCreateScoreService service = new HTTPCreateScoreService();
	}

	public static HTTPCreateScoreService getInstance() {
		return HTTPCreateScoreServiceSingleton.service;
	}

	@Override
	public void doServe(final HTTPRequest httpRequest, final HTTPResponse httpResponse) {

		LOGGER.debug("Invoking.. doServe() method");

		try {
			Integer levelId = Integer.parseInt((String) httpRequest.getParameters().get("levelid"));
			String sessionKey = (String) httpRequest.getParameters().get("sessionkey");
			Integer score = Integer.parseInt((String) httpRequest.getParameters().get("scores"));

			LOGGER.info("levelId:- ({}) received ", levelId);
			LOGGER.info("sessionKey:- ({}) received ", sessionKey);
			LOGGER.info("score:- ({}) received ", score);

			/**
			 * first checking levelId and score , they should exist in the request with
			 * number format > 0 and then checking the session validity
			 */
			if (!isLevelValid(levelId, httpResponse) || !isScoreValid(score, httpResponse)
					|| !isSessionValid(sessionKey, httpResponse)) {
				return;
			}

			final HTTPSession httpSession = isHTTPSessionFoundAndNotExpired(sessionKey, httpResponse);

			if (httpSession != null) {
				DataAccessor.getInstance().updateUserLevelWithScores(httpSession.getUser(), levelId, score);
				setResponseMessageAndCode(httpResponse, Constant.EMPTY_STRING,
						HTTPStatusCode.SUCCESSFUL_RESPONSE.STATUS_CODE);
				LOGGER.info("Post Score Request is servered successfully!!");
			}
		} catch (NumberFormatException e) {
			LOGGER.error(Constant.INTERNAL_EXCEPTION, e);
			setResponseMessageAndCode(httpResponse, HTTPStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR.MESSAGE,
					HTTPStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR.STATUS_CODE);
		} catch (Exception e) {
			LOGGER.error(Constant.INTERNAL_EXCEPTION, e);
			setResponseMessageAndCode(httpResponse, HTTPStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR.MESSAGE,
					HTTPStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR.STATUS_CODE);
		}
	}

	private boolean isLevelValid(final Integer levelId, final HTTPResponse httpResponse) {

		boolean flag = true;
		if (levelId < 0) {
			LOGGER.error(Constant.LEVEL_NOT_VALID, levelId);
			setResponseMessageAndCode(httpResponse, HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE,
					HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.STATUS_CODE);
			flag = false;
		}
		return flag;
	}

	private boolean isSessionValid(final String sessionKey, final HTTPResponse httpResponse) {

		boolean flag = true;
		if (sessionKey == null) {
			LOGGER.error(Constant.SESSION_ID_NOT_VALID, sessionKey);
			setResponseMessageAndCode(httpResponse, HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE,
					HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.STATUS_CODE);
			flag = false;
		}
		return flag;
	}

	private boolean isScoreValid(final Integer score, final HTTPResponse httpResponse) {
		boolean flag = true;
		if (score < 0) {
			LOGGER.error(Constant.SCORE_NOT_VALID, score);
			setResponseMessageAndCode(httpResponse, HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE,
					HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.STATUS_CODE);
			flag = false;
		}
		return flag;
	}

	private HTTPSession isHTTPSessionFoundAndNotExpired(final String sessionKey, final HTTPResponse httpResponse) {

		HTTPSession httpSession = DataAccessor.getInstance().retrieveHTTPSession(sessionKey);

		if (httpSession != null) {
			if (!DataAccessor.getInstance().isSessionValid(httpSession)) {
				// remove session from ConcurrentHashMap
				DataAccessor.getInstance().removeExpiredHTTPSession(sessionKey);
				LOGGER.error(Constant.SESSION_ID_EXPIRED, sessionKey);
				setResponseMessageAndCode(httpResponse, HTTPStatusCode.CLIENT_ERROR_401_UNAUTHORIZED_USER.MESSAGE,
						HTTPStatusCode.CLIENT_ERROR_401_UNAUTHORIZED_USER.STATUS_CODE);
				httpSession = null;
			}
		} else {
			LOGGER.error(Constant.SESSION_ID_NOT_FOUND, sessionKey);
			setResponseMessageAndCode(httpResponse, HTTPStatusCode.CLIENT_ERROR_401_UNAUTHORIZED_USER.MESSAGE,
					HTTPStatusCode.CLIENT_ERROR_401_UNAUTHORIZED_USER.STATUS_CODE);
		}
		return httpSession;
	}
}
