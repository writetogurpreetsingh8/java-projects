package com.king.httpserver.services.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.king.httpserver.constants.Constant;
import com.king.httpserver.core.DataAccessor;
import com.king.httpserver.core.HTTPSession;
import com.king.httpserver.core.User;
import com.king.httpserver.request.HTTPRequest;
import com.king.httpserver.response.HTTPResponse;
import com.king.httpserver.services.AbstractHTTPService;
import com.king.httpserver.services.filter.HTTPStatusCode;

public class HTTPLoginService extends AbstractHTTPService {

	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPLoginService.class);

	// thread safe implementation by static inner class
	private static class HTTPLoginServiceSingleton {
		private final static HTTPLoginService service = new HTTPLoginService();
	}

	public static HTTPLoginService getInstance() {
		return HTTPLoginServiceSingleton.service;
	}

	@Override
	public void doServe(final HTTPRequest httpRequest, final HTTPResponse httpResponse) {

		LOGGER.debug("Invoking.. doServe() method");
		try {

			final Integer userId = Integer.parseInt((String) httpRequest.getParameters().get("userid"));
			LOGGER.info("userId:- ({}) received ", userId);

			// user id must be unsigned integer number
			if (!isUserIdValid(userId, httpResponse)) {
				return;
			}

			LOGGER.debug("creating HTTPSession for userid:- ({}) ", userId);
			HTTPSession session = DataAccessor.getInstance().addNewHTTPSession();

			LOGGER.debug("creating new user...");
			final User user = new User();
			user.setUserId(userId);
			session.setUser(user);
			setResponseMessageAndCode(httpResponse, session.get_id(), HTTPStatusCode.SUCCESSFUL_RESPONSE.STATUS_CODE);
			LOGGER.info("Login Request is servered successfully!!");

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

	private boolean isUserIdValid(final int userId, final HTTPResponse httpResponse) {

		boolean flag = true;
		if (userId <= 0) {
			LOGGER.error(Constant.USER_ID_NOT_VALID, userId);
			setResponseMessageAndCode(httpResponse, HTTPStatusCode.CLIENT_ERROR_401_UNAUTHORIZED_USER.MESSAGE,
					HTTPStatusCode.CLIENT_ERROR_401_UNAUTHORIZED_USER.STATUS_CODE);
			flag = false;
		}
		return flag;
	}

}
