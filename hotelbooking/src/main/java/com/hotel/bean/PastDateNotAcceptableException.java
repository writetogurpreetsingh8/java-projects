package com.hotel.bean;

public class PastDateNotAcceptableException extends Exception{

	private static final long serialVersionUID = -4377081856264951242L;

	public PastDateNotAcceptableException() {
		super();
	}

	public PastDateNotAcceptableException(String message, Throwable cause) {
		super(message, cause);
	}

	public PastDateNotAcceptableException(String message) {
		super(message);
	}

}
