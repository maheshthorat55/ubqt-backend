package com.ubqt.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubqt.entity.SkillEvaluation;
import com.ubqt.model.SkillEvaluationRequest;
import com.ubqt.model.SkillEvaluationResponse;
import com.ubqt.repository.SkillEvaluationRepository;

@Service
public class SkillEvaluationServiceImpl implements SkillEvaluationService{

	@Autowired 
	private SkillEvaluationRepository skillEvaluationRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public SkillEvaluationResponse save(SkillEvaluationRequest skillEvaluationRequest) {
		SkillEvaluation evaluation = skillEvaluationRepository.findBySkillIdAndUserId(skillEvaluationRequest.getSkillId(), skillEvaluationRequest.getUserId());
		if(evaluation == null) {
			evaluation = this.modelMapper.map(skillEvaluationRequest, SkillEvaluation.class);
		} else {
			evaluation.setEvaluation(skillEvaluationRequest.getEvaluation());
		}
		evaluation = skillEvaluationRepository.save(evaluation);
		return this.modelMapper.map(evaluation, SkillEvaluationResponse.class);
	}
	
	@Override
	public Map<Long, SkillEvaluation> evaluatedSkills(Long userId) {
		List<SkillEvaluation> evaluatedSkills = skillEvaluationRepository.findAllByUserId(userId);
		return evaluatedSkills.stream().collect(Collectors.toMap(SkillEvaluation::getSkillId, Function.identity()));
	}

}
