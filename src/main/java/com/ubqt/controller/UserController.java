package com.ubqt.controller;

import javax.validation.Valid;

import com.ubqt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ubqt.model.UserRequest;
import com.ubqt.model.UserResponse;
import com.ubqt.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
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
}
