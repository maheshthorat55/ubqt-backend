package com.ubqt.service;

import java.util.Map;

import com.ubqt.entity.SkillEvaluation;
import com.ubqt.model.SkillEvaluationRequest;
import com.ubqt.model.SkillEvaluationResponse;

public interface SkillEvaluationService {
	SkillEvaluationResponse save(SkillEvaluationRequest skillEvaluationRequest);

	Map<Long, SkillEvaluation> evaluatedSkills(Long userId);
}
