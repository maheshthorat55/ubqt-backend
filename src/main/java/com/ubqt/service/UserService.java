package com.ubqt.service;

import java.util.Optional;

import javax.validation.Valid;

import com.ubqt.entity.User;
import com.ubqt.model.LoginRequest;
import com.ubqt.model.UserRequest;
import com.ubqt.model.UserResponse;

public interface UserService {
	UserResponse save(UserRequest user);

	Optional<UserResponse> findUser(@Valid LoginRequest loginRequest);

	Optional<User> findById(Long userId);

	Optional<User> findByMobileNumber(String mobileNumber);

	UserResponse updateUser(Long userId, @Valid UserRequest userRequest);
}
