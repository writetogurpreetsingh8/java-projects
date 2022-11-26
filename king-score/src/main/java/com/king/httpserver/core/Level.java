package com.king.httpserver.core;

import java.util.Collections;
import java.util.List;

public class Level {
	
	private int _id;
	private String levelName;
	private List<Integer> scores = Collections.emptyList();
	
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
}
