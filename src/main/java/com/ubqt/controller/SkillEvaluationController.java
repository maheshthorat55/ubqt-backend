package com.ubqt.controller;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubqt.entity.CareerManager;
import com.ubqt.entity.User;
import com.ubqt.exception.ResourceNotFound;
import com.ubqt.model.SkillEvaluationRequest;
import com.ubqt.model.SkillEvaluationResponse;
import com.ubqt.service.CareerManagerService;
import com.ubqt.service.SkillEvaluationService;
import com.ubqt.service.UserService;

@RestController
@RequestMapping("/evaluations")
public class SkillEvaluationController {

	@Autowired
	private SkillEvaluationService skillEvaluationService;
	
	@Autowired
	private CareerManagerService careerManagerService;
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<SkillEvaluationResponse> save(@Valid @RequestBody SkillEvaluationRequest skillEvaluationRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillEvaluationService.save(skillEvaluationRequest));
	}
	
	@PutMapping("/certify/{managerId}")
	public ResponseEntity<SkillEvaluationResponse> certifySkill(@PathVariable Long managerId, @Valid @RequestBody SkillEvaluationRequest skillEvaluationRequest) {
		Optional<CareerManager> careerManager = this.careerManagerService.findByManagerId(managerId);
		if(careerManager.isPresent()) {
			SkillEvaluationResponse response = this.skillEvaluationService.certifySkill(careerManager.get(), skillEvaluationRequest);
			Optional<User> oUser = userService.findById(skillEvaluationRequest.getUserId());
			if(oUser.isPresent()) {
				User user = oUser.get();
				user.setAssessed(1);
				user.setLastAssessed(LocalDateTime.now(ZoneOffset.UTC));
				this.userService.updateUser(user);
			}
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			throw new ResourceNotFound();
		}
	}
	
	@PutMapping("/for-user/{userId}/{managerId}/{skillId}")
	public ResponseEntity<SkillEvaluationResponse> updateSkill(@PathVariable Long userId, @PathVariable Long managerId, @PathVariable Long skillId, 
				@RequestBody Map<Object, Object> fields) {
		Optional<CareerManager> careerManager = this.careerManagerService.findByManagerId(managerId);
		if(careerManager.isPresent()) {
			SkillEvaluationResponse response = this.skillEvaluationService.updateSkill(careerManager.get(), userId, skillId, fields);
			Optional<User> oUser = userService.findById(userId);
			if(oUser.isPresent()) {
				User user = oUser.get();
				user.setAssessed(1);
				user.setLastAssessed(LocalDateTime.now(ZoneOffset.UTC));
				this.userService.updateUser(user);
			}
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			throw new ResourceNotFound();
		}
	}
	
	@PutMapping("/for-user/{userId}/{skillId}")
	public ResponseEntity<SkillEvaluationResponse> updateSkillForUser(@PathVariable Long userId, @PathVariable Long skillId, 
				@RequestBody Map<Object, Object> fields) {
			SkillEvaluationResponse response = this.skillEvaluationService.updateSkillForUser(userId, skillId, fields);
			return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/bulk-update")
	public ResponseEntity<Void> updateBulk(@Valid @RequestBody List<SkillEvaluationRequest> skillEvaluationRequest) {
		skillEvaluationService.addAll(skillEvaluationRequest);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
