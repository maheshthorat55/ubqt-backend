package com.ubqt.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubqt.entity.CareerManager;
import com.ubqt.entity.User;
import com.ubqt.exception.ResourceNotFound;
import com.ubqt.model.UserRequest;
import com.ubqt.model.UserResponse;
import com.ubqt.model.UserSearchRequest;
import com.ubqt.service.CareerManagerService;
import com.ubqt.service.SkillEvaluationService;
import com.ubqt.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CareerManagerService careerManagerService;
	
	@Autowired
	private SkillEvaluationService skillEvaluationService;
	
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	@PostMapping("/{userId}") // TODO need to change method type
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequest userRequest){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.updateUser(userId, userRequest));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId).get());
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersOrderByLastAssessed());
	}
	
	@DeleteMapping("/{phoneNumber}")
	public ResponseEntity<Void> deleteUserByPhoneNumber(@PathVariable String phoneNumber){
		userService.deleteUserByNumber(phoneNumber);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping("/search-by-skills")
	public ResponseEntity<List<User>> searchUserBySkills(@Valid @RequestBody List<Long> skils){
		Set<Long> userIds = this.skillEvaluationService.getUserIdsHavingSkills(skils);
		List<User> users = this.userService.getAllUsers(userIds);
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<User>> searchUserBySkillsAndExpert(@Valid @RequestBody UserSearchRequest userSearchRequest){
		Set<Long> userIds = this.skillEvaluationService.getUserIdsHavingSkillsAndExperts(userSearchRequest.getSkills());
		List<User> users = null;
		if(userSearchRequest.getAssessed() == 0) {
			users = this.userService.getAllUsers(userIds);
		} else {
			users = this.userService.getAllUsersAndAssessd(userIds, userSearchRequest.getAssessed());
		}
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@GetMapping("/for-manager/{managerId}")
	public ResponseEntity<List<User>> getUsers(@PathVariable Long managerId){
		List<User> users = null;
		if(managerId > 0) {
		  	Optional<CareerManager> careerManager = careerManagerService.findByManagerId(managerId);
		  	if(careerManager.isPresent()) {
		  		users = this.userService.getAllUsersByCareerManagerAndOrderByLastAssessed(careerManager.get());
		  	} else {
		  		throw new ResourceNotFound();
		  	}
		} else {
			users = this.userService.getAllUsersOrderByLastAssessed();
		}
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody Map<Object, Object> fields) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, fields));
	}
}
