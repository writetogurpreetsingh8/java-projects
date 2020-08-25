package com.hotel.bean;

public class RoomNotAvailableException extends Exception{

	private static final long serialVersionUID = -4377081856264951242L;

	public RoomNotAvailableException() {
		super();
	}

	public RoomNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoomNotAvailableException(String message) {
		super(message);
	}

}
