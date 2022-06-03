package com.ubqt.model;

import lombok.Data;

@Data
public class CareerManagerResponse {
	private long managerId;
	private String name;
	private String phoneNumber;
	private String email;
	private String preferedSkills;
	private String partner;
	private String power;
}
