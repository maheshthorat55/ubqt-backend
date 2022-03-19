package com.ubqt.service;

import com.ubqt.model.SkillEvaluationRequest;
import com.ubqt.model.SkillEvaluationResponse;

public interface SkillEvaluationService {
	SkillEvaluationResponse save(SkillEvaluationRequest skillEvaluationRequest);
}
