package com.king.httpserver.response;

import com.sun.net.httpserver.Headers;

public class HTTPResponse {
	
	private int httpStatusCode;
	private String httpResponseMessage;
	private Headers httpHeader;
	
	public HTTPResponse(Headers httpHeader) {
		super();
		this.httpHeader = httpHeader;
	}
	public int getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	public String getHttpResponseMessage() {
		return httpResponseMessage;
	}
	public void setHttpResponseMessage(String httpResponseMessage) {
		this.httpResponseMessage = httpResponseMessage;
	}
	public Headers getHttpHeader() {
		return httpHeader;
	}
	public void setHttpHeader(Headers httpHeader) {
		this.httpHeader = httpHeader;
	}
}