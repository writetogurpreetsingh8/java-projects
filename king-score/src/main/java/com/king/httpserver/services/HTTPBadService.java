package com.king.httpserver.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.king.httpserver.constants.Constant;
import com.king.httpserver.request.HTTPRequest;
import com.king.httpserver.response.HTTPResponse;

class HTTPBadService extends AbstractHTTPService {
	
	private static final long serialVersionUID = -1388221920034985613L;
	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPBadService.class);

	// prevent from reflection
	private HTTPBadService() throws HTTPServiceInstantiationException {
		if (getInstance() != null) {
			LOGGER.error("HTTPBadService is singleton by designed, can't make it clone... ");
			throw new HTTPServiceInstantiationException(new IllegalAccessException(Constant.REQUIRED_SINGLETON));
		}
		LOGGER.debug("HTTPBadService is initialized....... ");
		}

	// thread safe implementation by static inner class
	private static class HTTPBadServiceSingleton {
		private final static HTTPBadService service = new HTTPBadService();
	}

	public static HTTPBadService getInstance() {
		return HTTPBadServiceSingleton.service;
	}

	@Override
	public void doServe(final HTTPRequest httpRequest, final HTTPResponse httpResponse) {
		httpResponse.setHttpStatusCode((Integer) httpRequest.getParameters().get("errorCode"));
		httpResponse.setHttpResponseMessage((String) httpRequest.getParameters().get("errorMessage"));
	}
}
