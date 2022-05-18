package com.ubqt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubqt.entity.User;
import com.ubqt.exception.ResourceNotFound;
import com.ubqt.model.TalentMap.SkillResponse;
import com.ubqt.service.SkillMapService;
import com.ubqt.service.UserService;

@RestController
@RequestMapping("/skills")
public class SkillController {

	@Autowired
	private SkillMapService skillMapService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/talent-map/{stream}/{userId}")
	public ResponseEntity<List<SkillResponse[]>> getTalentMap(@PathVariable Long stream, @PathVariable Long templateId){
		return ResponseEntity.ok(skillMapService.getTalentMap(templateId));
	}
	
	@GetMapping("/talent-map/{userId}")
	public ResponseEntity<List<SkillResponse[]>> getTalentMapByUser(@PathVariable Long userId){
		Optional<User> userResponse = userService.findById(userId);
		if(userResponse.isPresent()) {
			User user = userResponse.get();
			if(user.getReferanceUser() != null && user.getReferanceUser().getTemplate() != null) {
				return ResponseEntity.ok(skillMapService.getTalentMap(user.getTemplate()));
			} else {
				throw new ResourceNotFound();
			}
		} else {
			throw new ResourceNotFound();
		}
	}
	
}
