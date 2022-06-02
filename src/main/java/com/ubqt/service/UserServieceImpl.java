package com.ubqt.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubqt.entity.User;
import com.ubqt.exception.ResourceNotFound;
import com.ubqt.model.LoginRequest;
import com.ubqt.model.UserRequest;
import com.ubqt.model.UserResponse;
import com.ubqt.model.UserSearchRequest;
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
		if(StringUtils.isNumeric(userRequest.getReferanceUserId())) {
			Optional<User> refUser = userRepository.findById(Long.parseLong(userRequest.getReferanceUserId()));
			if(refUser.isPresent()) {
				user.setReferanceUser(refUser.get());
			}
		} else {
			Optional<User> refUser = userRepository.findById(1L);
			if(refUser.isPresent()) {
				user.setReferanceUser(refUser.get());
			}
		}
		return modelMapper.map(userRepository.save(user), UserResponse.class);
	}

	@Override
	public Optional<UserResponse> findUser(@Valid LoginRequest loginRequest) {
		if(!StringUtils.isAllEmpty(loginRequest.getEmail(), loginRequest.getMobile(), loginRequest.getLinkdinId())) {
			Optional<User> user = Optional.empty();
			if(StringUtils.isNotEmpty(loginRequest.getEmail())) {
				user = userRepository.findByEmail(loginRequest.getEmail());
			} else if(StringUtils.isNotEmpty(loginRequest.getMobile())) {
				user = userRepository.findByMobileNumber(loginRequest.getEmail());
			} else {
				user = userRepository.findByLinkdinId(loginRequest.getEmail());
			}
			if(user.isPresent()) {
				return Optional.of(modelMapper.map(user.get(), UserResponse.class));
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<User> findById(Long userId) {
		return this.userRepository.findById(userId);
	}

	@Override
	public Optional<User> findByMobileNumber(String mobileNumber) {
		Optional<User>  uu=userRepository.findByMobileNumber(mobileNumber);
		return uu;
	}



	@Override
	public UserResponse updateUser(Long userId, @Valid UserRequest userRequest) {
		Optional<User> user = findById(userId);
		if(user.isPresent()) {
			User userUpdate = modelMapper.map(userRequest, User.class);
			userUpdate.setUserId(user.get().getUserId());
			userUpdate.setReferanceUser(user.get().getReferanceUser());
			return modelMapper.map(userRepository.save(userUpdate), UserResponse.class);
		} else {
			throw new ResourceNotFound();
		}
	}

	@Override
	public List<User> searchUsers(@Valid UserSearchRequest userSearchRequest) {
		return null;
	}

	@Override
	public List<User> getAllUsers(Set<Long> userIds) {
		return this.userRepository.findAllByUserIdIn(userIds);
	}

}
