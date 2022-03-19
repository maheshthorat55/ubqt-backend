package com.ubqt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubqt.entity.SkillMap;

public interface SkillMapRepository extends JpaRepository<SkillMap, Long> {

	List<SkillMap> findAllByStreamId(Long stream);

}
