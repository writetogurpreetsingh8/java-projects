package com.king.httpserver.request;

import java.util.Map;

public class HTTPRequest {
	
	private final Map<String,Object> parameters;
	
	public HTTPRequest(final Map<String,Object> parameters) {
		this.parameters = parameters;
	}

	public Map<String,Object> getParameters() {
		return parameters;
	}
}