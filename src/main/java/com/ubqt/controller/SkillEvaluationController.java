package com.ubqt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubqt.model.SkillEvaluationRequest;
import com.ubqt.model.SkillEvaluationResponse;
import com.ubqt.service.SkillEvaluationService;

@RestController
@RequestMapping("/evaluations")
public class SkillEvaluationController {

	@Autowired
	private SkillEvaluationService skillEvaluationService;

	@PostMapping
	public ResponseEntity<SkillEvaluationResponse> save(@Valid @RequestBody SkillEvaluationRequest skillEvaluationRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillEvaluationService.save(skillEvaluationRequest));
	}

}
