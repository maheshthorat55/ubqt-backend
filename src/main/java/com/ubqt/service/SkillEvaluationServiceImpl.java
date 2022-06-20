package com.ubqt.service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.ubqt.entity.CareerManager;
import com.ubqt.entity.SkillEvaluation;
import com.ubqt.exception.FieldNotFoundException;
import com.ubqt.exception.ResourceNotFound;
import com.ubqt.model.SearchSkill;
import com.ubqt.model.SkillEvaluationRequest;
import com.ubqt.model.SkillEvaluationResponse;
import com.ubqt.repository.SkillEvaluationRepository;
import com.ubqt.repository.SkillEvaluationRepositoryJdbcTemplate;
import com.ubqt.repository.UserRepositorydbcTemplate;

@Service
public class SkillEvaluationServiceImpl implements SkillEvaluationService{

	@Autowired 
	private SkillEvaluationRepository skillEvaluationRepository;
	
	@Autowired
	private UserRepositorydbcTemplate userRepositorydbcTemplate;
	
	@Autowired
	private SkillEvaluationRepositoryJdbcTemplate skillEvaluationRepositoryJdbcTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public SkillEvaluationResponse save(SkillEvaluationRequest skillEvaluationRequest) {
		SkillEvaluation evaluation = skillEvaluationRepository.findBySkillIdAndUserId(skillEvaluationRequest.getSkillId(), skillEvaluationRequest.getUserId());
		if(evaluation == null) {
			evaluation = this.modelMapper.map(skillEvaluationRequest, SkillEvaluation.class);
		} else {
			evaluation.setEvaluation(skillEvaluationRequest.getEvaluation());
			evaluation.setCertificationStatus(0);
		}
		evaluation = skillEvaluationRepository.save(evaluation);
		return this.modelMapper.map(evaluation, SkillEvaluationResponse.class);
	}
	
	@Override
	public Map<Long, SkillEvaluation> evaluatedSkills(Long userId) {
		List<SkillEvaluation> evaluatedSkills = skillEvaluationRepository.findAllByUserId(userId);
		return evaluatedSkills.stream().collect(Collectors.toMap(SkillEvaluation::getSkillId, Function.identity()));
	}

	@Override
	public Set<Long> getUserIdsHavingSkills(List<Long> skillIds) {
		return this.skillEvaluationRepository.findAllByskillIdInAndEvaluationGreaterThan(skillIds, 0L).stream().map(s -> s.getUserId())
				.collect(Collectors.toSet());
	}
	
	@Override
	public Set<Long> getUserIdsHavingSkillsAndExperts(List<SearchSkill> searchSkills) {
		List<Long> userIds = userRepositorydbcTemplate.findAllUserIds(searchSkills);
		return new HashSet<>(userIds);
	}

	@Override
	public SkillEvaluationResponse certifySkill(CareerManager careerManager,
			SkillEvaluationRequest skillEvaluationRequest) {
		SkillEvaluation evaluation = skillEvaluationRepository.findBySkillIdAndUserId(skillEvaluationRequest.getSkillId(), skillEvaluationRequest.getUserId());
		if(evaluation == null) {
			throw new ResourceNotFound();
		} else {
			evaluation.setEvaluation(skillEvaluationRequest.getEvaluation());
			evaluation.setCareerManager(careerManager);
			evaluation.setCertificationStatus(1);
			evaluation.setLastAssessed(LocalDateTime.now(ZoneOffset.UTC));
		}
		evaluation = skillEvaluationRepository.save(evaluation);
		return this.modelMapper.map(evaluation, SkillEvaluationResponse.class);
	}

	@Override
	public SkillEvaluationResponse updateSkill(CareerManager careerManager, Long userId,  Long skillId, Map<Object, Object> fields) {
		SkillEvaluation evaluation = skillEvaluationRepository.findBySkillIdAndUserId(skillId, userId);
		if(evaluation == null) {
			SkillEvaluation creatEvaluation = new SkillEvaluation();
			creatEvaluation.setUserId(userId);
			creatEvaluation.setSkillId(skillId);
			creatEvaluation.setCareerManager(careerManager);
			creatEvaluation.setCertificationStatus(1);
			fields.forEach((k,v) -> {
				Field field = ReflectionUtils.findField(SkillEvaluation.class, k.toString());
				if(field==null) {
					throw new FieldNotFoundException();
				}
				field.setAccessible(true);
				if(v != null) {
					ReflectionUtils.setField(field, creatEvaluation, getValueForType(field, v));
				}
			});
			creatEvaluation.setLastAssessed(LocalDateTime.now(ZoneOffset.UTC));
			return this.modelMapper.map(skillEvaluationRepository.save(creatEvaluation), SkillEvaluationResponse.class);
		} else {
			fields.forEach((k,v) -> {
				Field field = ReflectionUtils.findField(SkillEvaluation.class, k.toString());
				if(field==null) {
					throw new FieldNotFoundException();
				}
				field.setAccessible(true);
				if(v != null) {
					ReflectionUtils.setField(field, evaluation, getValueForType(field, v));
				}
			});
			evaluation.setLastAssessed(LocalDateTime.now(ZoneOffset.UTC));
			return this.modelMapper.map(skillEvaluationRepository.save(evaluation), SkillEvaluationResponse.class);
		}		
	}

	private Object getValueForType(Field field, Object v) {
		if(v==null) {
			return v;
		} else if(v.getClass() == field.getType()) {
			return v;
		} else if(Long.class.isAssignableFrom(field.getType())) {
			return Long.valueOf(v.toString());
		} else {
			return v;
		}
	}

	@Override
	public Map<Long, Long> findSkillSuplyCount() {
		return this.skillEvaluationRepositoryJdbcTemplate.findSkillSupplies();
	}

	@Override
	public SkillEvaluationResponse updateSkillForUser(Long userId, Long skillId, Map<Object, Object> fields) {
		SkillEvaluation evaluation = skillEvaluationRepository.findBySkillIdAndUserId(skillId, userId);
		if(evaluation == null) {
			SkillEvaluation creatEvaluation = new SkillEvaluation();
			fields.forEach((k,v) -> {
				Field field = ReflectionUtils.findField(SkillEvaluation.class, k.toString());
				if(field==null) {
					throw new FieldNotFoundException();
				}
				field.setAccessible(true);
				if(v != null) {
					ReflectionUtils.setField(field, creatEvaluation, getValueForType(field, v));
				}
			});
			return this.modelMapper.map(skillEvaluationRepository.save(creatEvaluation), SkillEvaluationResponse.class);
		} else {
			fields.forEach((k,v) -> {
				Field field = ReflectionUtils.findField(SkillEvaluation.class, k.toString());
				if(field==null) {
					throw new FieldNotFoundException();
				}
				field.setAccessible(true);
				if(v != null) {
					ReflectionUtils.setField(field, evaluation, getValueForType(field, v));
				}
			});
			return this.modelMapper.map(skillEvaluationRepository.save(evaluation), SkillEvaluationResponse.class);
		}
	}

	@Override
	public void addAll(@Valid List<SkillEvaluationRequest> skillEvaluationRequests) {
		skillEvaluationRequests.forEach(skillEvaluationRequest -> {
			SkillEvaluation evaluation = skillEvaluationRepository.findBySkillIdAndUserId(skillEvaluationRequest.getSkillId(), skillEvaluationRequest.getUserId());
			if(evaluation == null) {
				evaluation = new SkillEvaluation();
				evaluation.setCertificationStatus(1);
				evaluation.setSkillId(skillEvaluationRequest.getSkillId());
				evaluation.setEvaluation(skillEvaluationRequest.getEvaluation());
			} else {
				evaluation.setEvaluation(skillEvaluationRequest.getEvaluation());
				evaluation.setCertificationStatus(1);
				evaluation.setLastAssessed(LocalDateTime.now(ZoneOffset.UTC));
			}
			this.skillEvaluationRepository.save(evaluation);		
		});
	}

}
