package com.ubqt.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.ubqt.entity.CareerManager;
import com.ubqt.entity.Client;
import com.ubqt.entity.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubqt.exception.LoginUnauthorizedException;
import com.ubqt.model.CareerManagerResponse;
import com.ubqt.model.ClientResponse;
import com.ubqt.model.LoginRequest;
import com.ubqt.model.UserResponse;
import com.ubqt.service.CareerManagerService;
import com.ubqt.service.ClientService;
import com.ubqt.service.UserService;

@RestController
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CareerManagerService careerManagerService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest){
		Optional<User> user = userService.findByMobileNumber(loginRequest.getMobile());
		if(user.isPresent()) {
			UserResponse u=new UserResponse(); // TODO need to work here temp add this code
			u.setUserId(user.get().getUserId());
			return ResponseEntity.ok(u);
		} else {
			throw new LoginUnauthorizedException();
		}
	}
	
	@PostMapping("/client")
	public ResponseEntity<ClientResponse> loginClient(@Valid @RequestBody LoginRequest loginRequest){
		Optional<Client> client = clientService.findByMobileNumber(loginRequest.getMobile());
		if(client.isPresent()) {
			return ResponseEntity.ok(this.modelMapper.map(client.get(), ClientResponse.class));
		} else {
			throw new LoginUnauthorizedException();
		}
	}
	
	@PostMapping("/manager")
	public ResponseEntity<CareerManagerResponse> loginManager(@Valid @RequestBody LoginRequest loginRequest){
		Optional<CareerManager> client = careerManagerService.findByMobileNumber(loginRequest.getMobile());
		if(client.isPresent()) {
			return ResponseEntity.ok(this.modelMapper.map(client.get(), CareerManagerResponse.class));
		} else {
			throw new LoginUnauthorizedException();
		}
	}

}
