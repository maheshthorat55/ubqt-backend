package com.ubqt.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubqt.entity.User;
import com.ubqt.model.ShortListRequest;
import com.ubqt.service.ShortListService;
import com.ubqt.service.UserService;

@RestController
@RequestMapping("/shortlists")
public class ShortListController {
	
	@Autowired
	private ShortListService shortListService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Void> shortListUser(@Valid @RequestBody ShortListRequest user){
		this.shortListService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/{clientId}/{userId}")
	public ResponseEntity<Void> deleteShortList(@PathVariable Long clientId, @PathVariable Long userId){
		this.shortListService.delete(clientId, userId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/users/{clientId}")
	public ResponseEntity<List<User>> searchUser(@PathVariable Long clientId) {
		Set<Long> userIds = this.shortListService.getShortListedUsersFor(clientId);
		List<User> users = this.userService.getAllUsers(userIds).stream()
				//.sorted(Comparator.comparing(User::getSkillScore).reversed())
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

}
