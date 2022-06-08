package com.ubqt.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubqt.entity.Skill;
import com.ubqt.entity.SkillEvaluation;
import com.ubqt.entity.Template;
import com.ubqt.entity.User;
import com.ubqt.exception.ResourceNotFound;
import com.ubqt.model.TalentMap.SkillResponse;
import com.ubqt.service.SkillEvaluationService;
import com.ubqt.service.SkillMapService;
import com.ubqt.service.SkillService;
import com.ubqt.service.TemplateService;
import com.ubqt.service.UserService;

@RestController
@RequestMapping("/skills")
public class SkillController {

	@Autowired
	private SkillMapService skillMapService;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private SkillEvaluationService skillEvaluationService;
	
	@GetMapping("/talent-map/{stream}/{userId}")
	public ResponseEntity<List<SkillResponse[]>> getTalentMap(@PathVariable Long stream, @PathVariable Long templateId){
		return ResponseEntity.ok(skillMapService.getTalentMap(templateId));
	}
	
	@GetMapping("/talent-map/{userId}")
	public ResponseEntity<List<SkillResponse[]>> getTalentMapByUser(@PathVariable Long userId){
		Optional<User> userResponse = userService.findById(userId);
		if(userResponse.isPresent()) {
			User user = userResponse.get();
			if(user.getTemplate() != null || (user.getReferanceUser() != null && user.getReferanceUser().getTemplate() != null)) {
				Map<Long, SkillEvaluation> evaluatedSkills = skillEvaluationService.evaluatedSkills(userId);
				return ResponseEntity.ok(skillMapService.getTalentMap(getTemplate(user), evaluatedSkills));
			} else {
				throw new ResourceNotFound();
			}
		} else {
			throw new ResourceNotFound();
		}
	}
	
	@GetMapping("/talent-map-looking-for")
	public ResponseEntity<List<SkillResponse[]>> getTalentMapByUserForLookingFor(){
		Template template = templateService.getDefaultTemplate();
		if (template != null) {
			Map<Long, Long> skillSupply = skillEvaluationService.findSkillSuplyCount();
			return ResponseEntity.ok(skillMapService.getTalentMapWithSupply(template, skillSupply));
		} else {
			throw new ResourceNotFound();
		}
	}

	private Template getTemplate(User user) {
		Template template = user.getTemplate();
		if(user.getReferanceUser() != null && user.getReferanceUser().getTemplate() != null) {
			template = user.getReferanceUser().getTemplate();
		}
		return template;
	}
	
	@GetMapping("/hit-map/{userId}")
	public ResponseEntity<List<SkillResponse[]>> getHitMapByUser(@PathVariable Long userId){
		Optional<User> userResponse = userService.findById(userId);
		if(userResponse.isPresent()) {
			User user = userResponse.get();
			if(user.getTemplate() != null || (user.getReferanceUser() != null && user.getReferanceUser().getTemplate() != null)) {
				return ResponseEntity.ok(skillMapService.getHitMap(getTemplate(user)));
			} else {
				throw new ResourceNotFound();
			}
		} else {
			throw new ResourceNotFound();
		}
	}
	
	@GetMapping("/hit-map-for/{userId}")
	public ResponseEntity<List<SkillResponse[]>> getHitMapForUser(@PathVariable Long userId){
		Optional<User> userResponse = userService.findById(userId);
		if(userResponse.isPresent()) {
			User user = userResponse.get();
			if(user.getTemplate() != null || (user.getReferanceUser() != null && user.getReferanceUser().getTemplate() != null)) {
				Map<Long, SkillEvaluation> evaluatedSkills = skillEvaluationService.evaluatedSkills(userId);
				return ResponseEntity.ok(skillMapService.getHitMap(getTemplate(user), evaluatedSkills));
			} else {
				throw new ResourceNotFound();
			}
		} else {
			throw new ResourceNotFound();
		}
	}
	
	@PutMapping("/{skillId}")
	public ResponseEntity<Skill> updateDemandForSkill(@PathVariable Long skillId){
		Optional<Skill> skill = skillService.findById(skillId);
		if (skill.isPresent()) {
			Skill skillEntity = skill.get();
			skillEntity.setDemand(skillEntity.getDemand() != null ? skillEntity.getDemand() + 1 : 1);
			return ResponseEntity.ok(skillService.update(skillEntity));
		} else {
			throw new ResourceNotFound();
		}
	}
	
}
