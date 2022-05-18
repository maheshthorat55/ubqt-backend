package com.ubqt.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "template")
public class Template {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long templateId;
	
	private String name;
	
	private int defaultTemplate;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id", referencedColumnName = "id")
    private Set<User> users;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "stream_id", referencedColumnName = "id")
    private Set<SkillMap> skillMap;
	
	private Long createdBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
}
