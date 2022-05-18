package com.ubqt.service;

import java.util.List;

import com.ubqt.entity.Template;
import com.ubqt.model.TalentMap.SkillResponse;

public interface SkillMapService {
	List<SkillResponse[]> getTalentMap(Long talentMapId);
	
	List<SkillResponse[]> getTalentMap(Template template);
}
