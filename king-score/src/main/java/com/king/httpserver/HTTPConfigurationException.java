package com.king.httpserver;

public class HTTPConfigurationException extends RuntimeException {

	private static final long serialVersionUID = -567681211999815322L;

	public HTTPConfigurationException() {
    }
	
    public HTTPConfigurationException(String message) {
        super(message);
    }

    public HTTPConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public HTTPConfigurationException(Throwable cause) {
        super(cause);
    }
}
