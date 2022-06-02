package com.ubqt.model;

import lombok.Data;

@Data
public class ClientResponse {
	private long clientId;
	private String name;
	private String phoneNumber;
	private String email;
	private String skills;
	private String account;
	private String text1;
}
