package com.king.httpserver.core;

import java.util.Collections;
import java.util.Map;

public class User {
	
	private int userId;
	private Map<Integer,Level> levels = Collections.emptyMap();
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Map<Integer, Level> getLevels() {
		return levels;
	}
	public void setLevels(Map<Integer, Level> levels) {
		this.levels = levels;
	}
}
