package com.king.httpserver.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HTTPSessionsHolder hold all the sessions of all the users 
 */
public abstract class HTTPSessionsHolder {
	
	private final static Map<String, HTTPSession> sessions = new ConcurrentHashMap<String, HTTPSession>();
	
	public static Map<String, HTTPSession> getSessions() {
		return HTTPSessionsHolder.sessions;
	}
}
