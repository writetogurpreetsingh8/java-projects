package com.king.httpserver.services;

import com.king.httpserver.request.HTTPRequest;
import com.king.httpserver.response.HTTPResponse;

class HTTPBadService extends AbstractHTTPService {
	
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
