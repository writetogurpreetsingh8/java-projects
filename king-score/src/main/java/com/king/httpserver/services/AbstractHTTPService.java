package com.king.httpserver.services;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.king.httpserver.constants.Constant;
import com.king.httpserver.request.HTTPRequest;
import com.king.httpserver.response.HTTPResponse;

public abstract class AbstractHTTPService implements Cloneable, Serializable  {
	
	private static final long serialVersionUID = -5970991583603551822L;
	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractHTTPService.class);

	AbstractHTTPService() {}
	
	public abstract void doServe(final HTTPRequest httpRequest, final HTTPResponse httpResponse);
	
	void setResponseMessageAndCode(final HTTPResponse httpResponse, final String responseMessage, final int responseCode) {
		httpResponse.setHttpResponseMessage(responseMessage);
		httpResponse.setHttpStatusCode(responseCode);
	}
	
	void setResponseHeader(final HTTPResponse httpResponse, final String responseHeaderKey, final String responseHeaderValue) {
		httpResponse.getHttpHeader().add(responseHeaderKey, responseHeaderValue);
		httpResponse.getHttpHeader().add(responseHeaderKey, responseHeaderValue);
	}
	
	// return same instance while trying to Deserialize
	protected Object readResolve() {
		return this;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		LOGGER.error("{} class is singleton by designed, can't make it clone... ",this.getClass().getName());
		throw new CloneNotSupportedException(Constant.CLONE_NOT_SUPPORTED);
	}
}
