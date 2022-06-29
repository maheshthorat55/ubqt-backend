package com.ubqt.service;

import java.util.List;
import java.util.Optional;

import com.ubqt.entity.CareerManager;
import com.ubqt.model.CareerManagerRequest;
import com.ubqt.model.CareerManagerResponse;

public interface CareerManagerService {

	CareerManagerResponse save(CareerManagerRequest careerManager);

	CareerManagerResponse updateClient(Long managerId, CareerManagerRequest careerManager);

	CareerManagerResponse findById(Long managerId);
	
	Optional<CareerManager> findByManagerId(Long managerId);

	Optional<CareerManager> findByMobileNumber(String mobile);

	CareerManager create(CareerManagerRequest careerManagerRequest);

	List<CareerManager> findAll();

	void deleteById(Long managerId);

	void deleteByPhoneNumber(String phoneNumber);

}
