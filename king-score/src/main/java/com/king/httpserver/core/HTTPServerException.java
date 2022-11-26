package com.king.httpserver.core;

public class HTTPServerException extends RuntimeException{
	
	private static final long serialVersionUID = -6875417752130932659L;

	public HTTPServerException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public HTTPServerException(Throwable cause) {
		super(cause);
	}

	public HTTPServerException(String message) {
		super(message);
	}
}
