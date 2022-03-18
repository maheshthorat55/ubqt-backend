package com.ubqt.service;

import com.ubqt.model.UserRequest;
import com.ubqt.model.UserResponse;

public interface UserService {
	UserResponse save(UserRequest user);
}
