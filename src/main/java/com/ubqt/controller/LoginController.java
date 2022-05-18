package com.ubqt.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubqt.exception.LoginUnauthorizedException;
import com.ubqt.model.LoginRequest;
import com.ubqt.model.UserResponse;
import com.ubqt.service.UserService;

@RestController
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest){
		Optional<UserResponse> user = userService.findUser(loginRequest);
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			throw new LoginUnauthorizedException();
		}
	}

}
