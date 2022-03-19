package com.ubqt.model;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillEvaluationRequest {
	@NotNull(message = "skillId should not be null")
	private Long skillId;
	@NotNull(message = "evaluation should not be null")
	private Long evaluation;
	@NotNull(message = "userId should not be null")
	private Long userId;
}
