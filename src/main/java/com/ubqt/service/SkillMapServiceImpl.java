package com.ubqt.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubqt.entity.Category;
import com.ubqt.entity.Skill;
import com.ubqt.entity.SkillMap;
import com.ubqt.model.TalentMap;
import com.ubqt.model.TalentMap.CategoryResponse;
import com.ubqt.model.TalentMap.SkillResponse;
import com.ubqt.repository.SkillMapRepository;
import com.ubqt.util.ObjectMapperUtils;

@Service
public class SkillMapServiceImpl implements SkillMapService {

	@Autowired
	private SkillMapRepository skillMapRepository; 
	
	@Override
	public TalentMap getTalentMap(Long stream) {
		List<SkillMap> skillMaps = skillMapRepository.findAllByStreamId(stream);
		Map<Category, List<Skill>> categories = skillMaps.stream().map(skillMap -> skillMap.getSkill())
				.collect(Collectors.groupingBy(Skill::getCategory));
		TalentMap talentMap = TalentMap.builder().build();
		talentMap.setStreamId(stream);
		List<CategoryResponse> categoryResponseList = new ArrayList<>();
		categories.entrySet().forEach(category -> {
			categoryResponseList.add(CategoryResponse.builder().id(category.getKey().getId())
					.name(category.getKey().getName()).skills(mapToSkillResponse(category.getValue()))
					.position(category.getKey().getPosition()).build());
		});
		talentMap.setCategories(categoryResponseList.stream().sorted().collect(Collectors.toList()));
		return talentMap;
	}
	
	private List<SkillResponse> mapToSkillResponse(List<Skill> skills) {
		return ObjectMapperUtils.mapAll(skills, SkillResponse.class).stream()
				.sorted(Comparator.comparing(SkillResponse::getPosition)).collect(Collectors.toList());
	}


}
