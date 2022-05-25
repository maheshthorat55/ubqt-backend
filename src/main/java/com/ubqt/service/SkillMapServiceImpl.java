package com.ubqt.service;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ubqt.entity.Category;
import com.ubqt.entity.Skill;
import com.ubqt.entity.SkillEvaluation;
import com.ubqt.entity.SkillMap;
import com.ubqt.entity.Template;
import com.ubqt.model.TalentMap.CategoryResponse;
import com.ubqt.model.TalentMap.SkillResponse;
import com.ubqt.repository.SkillMapRepository;
import com.ubqt.repository.TemplateRepository;
import com.ubqt.util.Constant;
import com.ubqt.util.ObjectMapperUtils;

@Service
public class SkillMapServiceImpl implements SkillMapService {

	@Autowired
	private SkillMapRepository skillMapRepository;

	@Autowired
	private TemplateRepository templateRepository;
	
	@Value("${ubqt.skillSize}")
	private int skillSize;

	@Override
	public List<SkillResponse[]> getTalentMap(Long stream) {
		Template template = templateRepository.findById(stream).get();
		return getSkillListByTemplate(template, new HashMap<Long, SkillEvaluation>());
	}

	private List<SkillResponse[]> getSkillListByTemplate(Template template, Map<Long, SkillEvaluation> evaluatedSkills) {
		List<SkillMap> skillMaps = skillMapRepository.findAllByTemplate(template);
		Map<Category, List<Skill>> categories = skillMaps.stream().map(skillMap -> skillMap.getSkill())
				.collect(Collectors.groupingBy(Skill::getCategory));

		List<CategoryResponse> categoryResponseList = new ArrayList<>();

		int maxSkills = getMaxSkillSize(categories);

		categories.entrySet().forEach(category -> {
			categoryResponseList.add(CategoryResponse.builder().id(category.getKey().getId())
					.name(category.getKey().getName()).skills(mapToSkillResponse(category.getValue()))
					.shortName(category.getKey().getShortName()).position(category.getKey().getPosition())
					.demand(category.getKey().getDemand()).color(category.getKey().getColor()).build());
		});

		List<CategoryResponse> cat = categoryResponseList.stream().sorted().collect(Collectors.toList());
		List<SkillResponse[]> response = new ArrayList<>();
		SkillResponse[] SkillTalent = new SkillResponse[cat.size()];
		for (int i = 0; i < maxSkills; i++) {
			SkillTalent = new SkillResponse[cat.size()];
			response.add(SkillTalent);
		}
		int index = 0;
		int indexSkill = 0;
		SkillTalent = new SkillResponse[cat.size()];
		for (CategoryResponse categoryResponse : cat) {
			indexSkill = 0;
			SkillTalent[index] = SkillResponse.builder()
							.id(categoryResponse.getId())
							.demand(categoryResponse.getDemand())
							.name(categoryResponse.getName())
							.shortName(categoryResponse.getShortName())
							.build();
			for (SkillResponse skill : categoryResponse.getSkills()) {
				SkillEvaluation skillEvaluation = evaluatedSkills.get(skill.getId());
				if(skillEvaluation != null && skillEvaluation.getEvaluation() >0) {
					skill.setRating(skillEvaluation.getEvaluation());
					String[]color= StringUtils.split(categoryResponse.getColor(), ",");
					skill.setColor(color[skillEvaluation.getEvaluation().intValue()-1] );
					skill.setTextColor("#FFFFFF");
				}else{
					skill.setColor("#FFFFFF");
					skill.setTextColor("#000000");
				}
				skill.setColorCodes(StringUtils.split(categoryResponse.getColor(), ","));
				response.get(indexSkill)[index] = skill;
				indexSkill++;
			}
			index++;
		}
		if(response.size() > skillSize) {
			response = response.subList(0, skillSize);
		}
		Collections.reverse(response);
		for(SkillResponse sk:SkillTalent){
			sk.setColor("#595959"); // TODO: will remove hardcoded for category
			sk.setTextColor("#FFFFFF");
		}
		response.add(SkillTalent);
		return response;
	}

	private int getMaxSkillSize(Map<Category, List<Skill>> categories) {
		int maxSkills = 0;
		Optional<Entry<Category, List<Skill>>> entry = categories.entrySet().stream()
				.max(Comparator.comparingInt(value -> value.getValue().size()));
		if (entry.isPresent()) {
			maxSkills = entry.get().getValue().size();
		}
		return maxSkills;
	}

	private List<SkillResponse> mapToSkillResponse(List<Skill> skills) {
		return ObjectMapperUtils.mapAll(skills, SkillResponse.class).stream()
				.sorted(Comparator.comparing(SkillResponse::getDemand).reversed()).collect(Collectors.toList());
	}

	@Override
	public List<SkillResponse[]> getTalentMap(Template template, Map<Long, SkillEvaluation> evaluatedSkills) {
		return getSkillListByTemplate(template, evaluatedSkills);
	}

	@Override
	public List<SkillResponse[]> getHitMap(Template template) {
		return getHitMapByTemplate(template, null);
	}

	private List<SkillResponse[]> getHitMapByTemplate(Template template, Map<Long, SkillEvaluation> evaluatedSkills) {
		List<SkillMap> skillMaps = skillMapRepository.findAllByTemplate(template);
		
		
		List<Skill> skills = skillMaps.stream().map(skillMap -> skillMap.getSkill()).
							sorted(Comparator.comparing(Skill::getDemand).reversed())
							.collect(Collectors.toList());
		
		int hitMapFactor = skills.size() / 16;
		int index = 0;
		for (Skill skill : skills) {
			int hitMapIndex = Math.floorDiv(index, hitMapFactor);
			skill.setColor(Constant.hitMapColors.get(hitMapIndex > 15 ? 15 : hitMapIndex));
			index++;
		}
		Map<Category, List<Skill>> categories = skills.stream().collect(Collectors.groupingBy(Skill::getCategory));
		List<CategoryResponse> categoryResponseList = new ArrayList<>();
		int maxSkills = getMaxSkillSize(categories);
		categories.entrySet().forEach(category -> {
			categoryResponseList.add(CategoryResponse.builder().id(category.getKey().getId())
					.name(category.getKey().getName()).skills(mapToSkillResponse(category.getValue()))
					.shortName(category.getKey().getShortName())
					.demand(category.getKey().getDemand())
					.position(category.getKey().getPosition())
					.color(category.getKey().getColor()).build());
		});

		List<CategoryResponse> cat = categoryResponseList.stream().sorted().collect(Collectors.toList());
		List<SkillResponse[]> response = new ArrayList<>();
		SkillResponse[] SkillTalent = new SkillResponse[cat.size()];
		for (int i = 0; i < maxSkills; i++) {
			SkillTalent = new SkillResponse[cat.size()];
			response.add(SkillTalent);
		}
		index = 0;
		int indexSkill = 0;
		SkillTalent = new SkillResponse[cat.size()];
		for (CategoryResponse categoryResponse : cat) {
			indexSkill = 0;
			SkillTalent[index] = SkillResponse.builder()
							.id(categoryResponse.getId())
							.demand(categoryResponse.getDemand())
							.name(categoryResponse.getName())
							.shortName(categoryResponse.getShortName())
							.build();
			for (SkillResponse skill : categoryResponse.getSkills()) {
				if(evaluatedSkills != null) {
					SkillEvaluation skillEvaluation = evaluatedSkills.get(skill.getId());
					if(skillEvaluation != null && skillEvaluation.getEvaluation() > 0) {
						skill.setRating(skillEvaluation.getEvaluation());
					}
				}
				skill.setTextColor("#000000");
				skill.setColorCodes(StringUtils.split(categoryResponse.getColor(), ","));
				response.get(indexSkill)[index] = skill;
				indexSkill++;
			}
			index++;
		}
		if(response.size() > skillSize) {
			response = response.subList(0, skillSize);
		}
		Collections.reverse(response);
		for(SkillResponse sk:SkillTalent){
			sk.setColor("#595959"); // TODO: will remove hardcoded for category
			sk.setTextColor("#FFFFFF");
		}
		response.add(SkillTalent);
		return response;
	}

	@Override
	public List<SkillResponse[]> getHitMap(Template template, Map<Long, SkillEvaluation> evaluatedSkills) {
		return getHitMapByTemplate(template, evaluatedSkills);
	}

}
