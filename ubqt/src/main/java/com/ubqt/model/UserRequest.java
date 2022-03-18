package com.ubqt.model;

public class UserRequest {
	private String userName;
	private String mobileNumber;
	private String email;
	private String linkdinId;
	private String type;
	private String referanceUserId;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReferanceUserId() {
		return referanceUserId;
	}
	public void setReferanceUserId(String referanceUserId) {
		this.referanceUserId = referanceUserId;
	}
}
