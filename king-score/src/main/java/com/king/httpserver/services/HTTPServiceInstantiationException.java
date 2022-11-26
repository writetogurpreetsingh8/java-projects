package com.king.httpserver.services;

public class HTTPServiceInstantiationException extends RuntimeException {

	private static final long serialVersionUID = -2818222388704315626L;

	public HTTPServiceInstantiationException(String message, Throwable cause) {
		super(message, cause);
	}

	public HTTPServiceInstantiationException(Throwable cause) {
		super(cause);
	}

}
