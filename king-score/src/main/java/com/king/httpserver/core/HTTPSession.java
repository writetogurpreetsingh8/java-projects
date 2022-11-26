package com.king.httpserver.core;

import java.time.Instant;

public class HTTPSession {
	
	private String _id;
	private User user;
	private Instant createdOn;
	private Instant validateUpto;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Instant getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}
	public Instant getValidateUpto() {
		return validateUpto;
	}
	public void setValidateUpto(Instant validateUpto) {
		this.validateUpto = validateUpto;
	}
}
