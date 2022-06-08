package com.ubqt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubqt.entity.Skill;
import com.ubqt.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;
	
	@Override
	public Optional<Skill> findById(Long skillId) {
		return skillRepository.findById(skillId);
	}

	@Override
	public Skill update(Skill skillEntity) {
		return skillRepository.save(skillEntity);
	}

}
