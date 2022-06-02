package com.ubqt.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.ubqt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ubqt.model.UserRequest;
import com.ubqt.model.UserResponse;
import com.ubqt.model.UserSearchRequest;
import com.ubqt.service.SkillEvaluationService;
import com.ubqt.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
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
	
	@PostMapping("/search")
	public ResponseEntity<List<User>> searchUser(@Valid @RequestBody UserSearchRequest userSearchRequest){
		Set<Long> userIds = this.skillEvaluationService.getUserIdsHavingSkills(userSearchRequest.getSkillIds());
		List<User> users = this.userService.getAllUsers(userIds);
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
}
