package com.king.httpserver.services;

import com.king.httpserver.request.HTTPRequest;
import com.king.httpserver.response.HTTPResponse;

public abstract class AbstractHTTPService  {
	
	public abstract void doServe(final HTTPRequest httpRequest, final HTTPResponse httpResponse);
	
	protected void setResponseMessageAndCode(final HTTPResponse httpResponse, final String responseMessage, final int responseCode) {
		httpResponse.setHttpResponseMessage(responseMessage);
		httpResponse.setHttpStatusCode(responseCode);
	}
	
	protected void setResponseHeader(final HTTPResponse httpResponse, final String responseHeaderKey, final String responseHeaderValue) {
		httpResponse.getHttpHeader().add(responseHeaderKey, responseHeaderValue);
		httpResponse.getHttpHeader().add(responseHeaderKey, responseHeaderValue);
	}
}
