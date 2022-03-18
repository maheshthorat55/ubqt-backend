package com.ubqt.model;

public class UserResponse {
	private long userId;
	private String userName;
	private String mobileNumber;
	private String email;
	private String linkdinId;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLinkdinId() {
		return linkdinId;
	}
	public void setLinkdinId(String linkdinId) {
		this.linkdinId = linkdinId;
	}
}
