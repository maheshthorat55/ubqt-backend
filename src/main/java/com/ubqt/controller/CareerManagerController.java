package com.ubqt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubqt.entity.CareerManager;
import com.ubqt.model.CareerManagerRequest;
import com.ubqt.model.CareerManagerResponse;
import com.ubqt.service.CareerManagerService;

@RestController
@RequestMapping("career-manager")
public class CareerManagerController {

	@Autowired
	private CareerManagerService careerManagerService;
	
	@PostMapping
	public ResponseEntity<CareerManagerResponse> createUser(@Valid @RequestBody CareerManagerRequest careerManager){
		return ResponseEntity.status(HttpStatus.CREATED).body(careerManagerService.save(careerManager));
	}
	
	@PutMapping("/{managerId}")
	public ResponseEntity<CareerManagerResponse> updateUser(@PathVariable Long managerId, @Valid @RequestBody CareerManagerRequest careerManager){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(careerManagerService.updateClient(managerId, careerManager));
	}

	@GetMapping("/{managerId}")
	public ResponseEntity<CareerManagerResponse> getUser(@PathVariable Long managerId){
		return ResponseEntity.status(HttpStatus.OK).body(careerManagerService.findById(managerId));
	}
	
	@GetMapping
	public ResponseEntity<List<CareerManager>> getAllCareerManager(){
		return ResponseEntity.status(HttpStatus.OK).body(careerManagerService.findAll());
	}
}
