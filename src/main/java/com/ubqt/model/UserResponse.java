package com.ubqt.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
	private long userId;
	private String userName;
	private String mobileNumber;
	private String email;
	private String linkdinId;
	private String name;
	private Double salary;
	private Integer isAvailable;
	private String availabilityNotes;
	private Integer redFlag;
	private String redFlagNotes;
	private Integer skillScore;
	private LocalDateTime lastAssessed;
	private Integer assessed;
	private String currentOrg;
	private String aboutYou;
	private String city;
	private String text1;
	private String text2;
}
