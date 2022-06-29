package com.ubqt.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import com.ubqt.entity.CareerManager;
import com.ubqt.entity.SkillEvaluation;
import com.ubqt.model.SearchSkill;
import com.ubqt.model.SkillEvaluationRequest;
import com.ubqt.model.SkillEvaluationResponse;

public interface SkillEvaluationService {
	SkillEvaluationResponse save(SkillEvaluationRequest skillEvaluationRequest);

	Map<Long, SkillEvaluation> evaluatedSkills(Long userId);

	Set<Long> getUserIdsHavingSkills(List<Long> skillIds);

	Set<Long> getUserIdsHavingSkillsAndExperts(List<SearchSkill> searchSkills);

	SkillEvaluationResponse certifySkill(CareerManager careerManager, SkillEvaluationRequest skillEvaluationRequest);

	SkillEvaluationResponse updateSkill(CareerManager careerManager, Long userId, Long skillId,
			Map<Object, Object> fields);

	Map<Long, Long> findSkillSuplyCount();

	SkillEvaluationResponse updateSkillForUser(Long userId, Long skillId, Map<Object, Object> fields);

	void addAll(@Valid List<SkillEvaluationRequest> skillEvaluationRequest);

	void updateSkillScore(Long userId);
}
