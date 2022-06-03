package com.ubqt.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "career_manager")
public class CareerManager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manager_id")
	private long managerId;
	
	private String name;
	private String phoneNumber;
	private String email;
	private String preferedSkills;
	private String partner;
	private String power;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "career_manager", referencedColumnName = "manager_id")
    private Set<User> users;

}
