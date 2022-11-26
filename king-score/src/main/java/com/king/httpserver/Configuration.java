package com.king.httpserver;

/**
 * 
 * it will hold the basic initial configuration as given below
 */
public class Configuration {
	
	private String webContext;
	private int serverPort;
	private int backlog;
	private int sessionValidUptoMins;
	
	/**
	 * only getter methods not need of setters here
	 */
	
	public String getWebContext() {
		return webContext;
	}
	public int getServerPort() {
		return serverPort;
	}
	public int getBacklog() {
		return backlog;
	}
	public int getSessionValidUptoMins() {
		return sessionValidUptoMins;
	}
}
