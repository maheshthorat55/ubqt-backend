package com.ubqt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubqt.entity.SkillEvaluation;

public interface SkillEvaluationRepository extends JpaRepository<SkillEvaluation, Long> {

	SkillEvaluation findBySkillIdAndUserId(Long skillId, Long userId);

	List<SkillEvaluation> findAllByUserId(Long userId);

}
