package com.ubqt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillEvaluationResponse {
	private Long skillId;
	private Long evaluation;
	private Long userId;
	private Integer certificationStatus;
	private String assessment;
	private String learnPlan;
}
