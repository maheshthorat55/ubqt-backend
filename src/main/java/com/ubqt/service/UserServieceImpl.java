package com.ubqt.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubqt.entity.User;
import com.ubqt.model.UserRequest;
import com.ubqt.model.UserResponse;
import com.ubqt.repository.UserRepository;

@Service
public class UserServieceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserResponse save(UserRequest userRequest) {
		User user = modelMapper.map(userRequest, User.class);
		return modelMapper.map(userRepository.save(user), UserResponse.class);
	}

}
