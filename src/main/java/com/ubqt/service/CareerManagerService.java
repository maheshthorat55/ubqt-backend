package com.ubqt.service;

import java.util.Optional;

import com.ubqt.entity.CareerManager;
import com.ubqt.model.CareerManagerRequest;
import com.ubqt.model.CareerManagerResponse;

public interface CareerManagerService {

	CareerManagerResponse save(CareerManagerRequest careerManager);

	CareerManagerResponse updateClient(Long managerId, CareerManagerRequest careerManager);

	CareerManagerResponse findById(Long managerId);

	Optional<CareerManager> findByMobileNumber(String mobile);

}
