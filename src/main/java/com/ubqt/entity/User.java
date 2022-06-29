package com.ubqt.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;
	
	private String userName;
	
	private String mobileNumber;
	
	private String email;
	
	private String linkdinId;
	
	@ColumnDefault(value = "1")
	private Integer isAvailable;
	private String availabilityNotes;
	@ColumnDefault(value = "0")
	private Integer redFlag;
	private String redFlagNotes;
	
	@ColumnDefault(value = "0")
	private Integer assessed;
	
	@ColumnDefault(value = "0")
	private Integer skillScore;
		
	@OneToOne
	@JoinColumn(name = "referance_user", table = "user")
	@JsonBackReference
	private User referanceUser;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="career_manager", referencedColumnName = "manager_id")
	private CareerManager careerManager;
	
	private LocalDateTime lastAssessed;
	
	private String type;
	
	@ManyToOne
    @JoinColumn(name="template_id", referencedColumnName = "id")
	@JsonBackReference
    private Template template;
	
	private Date lastLogin;
	
	@ColumnDefault(value = "true")
	@Generated(GenerationTime.INSERT)
	private Boolean active;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private String name;
	private Double salary;
	private String currentOrg;
	private String aboutYou;
	private String city;
	private String text1;
	private String text2;
}
