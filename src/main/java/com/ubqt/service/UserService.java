package com.ubqt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import com.ubqt.entity.CareerManager;
import com.ubqt.entity.User;
import com.ubqt.model.LoginRequest;
import com.ubqt.model.UserRequest;
import com.ubqt.model.UserResponse;
import com.ubqt.model.UserSearchRequest;

public interface UserService {
	UserResponse save(UserRequest user);

	Optional<UserResponse> findUser(@Valid LoginRequest loginRequest);

	Optional<User> findById(Long userId);

	Optional<User> findByMobileNumber(String mobileNumber);

	UserResponse updateUser(Long userId, @Valid UserRequest userRequest);

	List<User> searchUsers(@Valid UserSearchRequest userSearchRequest);

	List<User> getAllUsers(Set<Long> userIds);

	void updateUser(User user);

	List<User> getAllUsersAndAssessd(Set<Long> userIds, int assessed);

	List<User> getAllUsersOrderByLastAssessed();

	List<User> getAllUsersByCareerManagerAndOrderByLastAssessed(CareerManager careerManager);

	User updateUser(Long userId, Map<Object, Object> fields);

	User create(UserRequest userRequest);

	void saveToRepository(User userEntity);

	void deleteUserByNumber(String phoneNumber);
}
