package com.ubqt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubqt.model.TalentMap;
import com.ubqt.service.SkillMapService;

@RestController
@RequestMapping("/skills")
public class SkillController {

	@Autowired
	private SkillMapService skillMapService;
	
	@GetMapping("/talent-map/{stream}/{userId}")
	public ResponseEntity<TalentMap> getTalentMap(@PathVariable Long stream, @PathVariable Long userId){
		return ResponseEntity.ok(skillMapService.getTalentMap(stream));
	}
	
}
