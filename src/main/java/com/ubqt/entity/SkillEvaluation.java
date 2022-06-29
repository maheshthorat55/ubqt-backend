package com.ubqt.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "skill_evaluation")
public class SkillEvaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private Long skillId;
	private Long evaluation;
	private Long userId;
	private Long experience;
	private Integer score;
	
	@ColumnDefault(value = "0")
	private Integer certificationStatus;
	
	@ManyToOne
    @JoinColumn(name="career_manager", referencedColumnName = "manager_id")
	private CareerManager careerManager;
	
	private String assessment;
	
	private LocalDateTime lastAssessed;
	
	private Long createdBy;
	private Long modifiedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
}
