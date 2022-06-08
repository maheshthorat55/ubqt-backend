package com.ubqt.service;

import java.util.Optional;

import com.ubqt.entity.Skill;

public interface SkillService {

	Optional<Skill> findById(Long skillId);

	Skill update(Skill skillEntity);

}
