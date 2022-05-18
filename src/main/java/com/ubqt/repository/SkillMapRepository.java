package com.ubqt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubqt.entity.SkillMap;
import com.ubqt.entity.Template;

public interface SkillMapRepository extends JpaRepository<SkillMap, Long> {

	List<SkillMap> findAllByTemplate(Template template);

}
