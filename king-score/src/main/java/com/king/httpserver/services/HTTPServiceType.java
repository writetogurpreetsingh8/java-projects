package com.king.httpserver.services;
public enum HTTPServiceType {
	
	LOGIN_SERVICE("login"),
	SCORE_SERVICE("score"),
	HIGEST_SCORE_LIST_SERVICE("highscorelist"),
	BAD_SERVICE("bad");
	
	public final String name;
	HTTPServiceType(final String name){
		this.name = name;
	}
}
