package com.ubqt.model;

import lombok.Data;

@Data
public class UserRequest {
	private String userName;
	private String mobileNumber;
	private String email;
	private String linkdinId;
	private String type;
	private String referanceUserId;
	private String name;
	private Double salary;
	private String currentOrg;
	private String aboutYou;
	private String city;
	private String text1;
	private String text2;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getCurrentOrg() {
		return currentOrg;
	}

	public void setCurrentOrg(String currentOrg) {
		this.currentOrg = currentOrg;
	}

	public String getAboutYou() {
		return aboutYou;
	}

	public void setAboutYou(String aboutYou) {
		this.aboutYou = aboutYou;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
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
