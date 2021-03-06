package com.ubqt.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubqt.entity.CareerManager;
import com.ubqt.exception.ResourceNotFound;
import com.ubqt.model.CareerManagerRequest;
import com.ubqt.model.CareerManagerResponse;
import com.ubqt.repository.CareerManagerRepository;

@Service
public class CareerManagerServiceImpl implements CareerManagerService {

	@Autowired
	private CareerManagerRepository careerManagerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CareerManagerResponse save(CareerManagerRequest careerManager) {
		CareerManager careerManagerEntity = modelMapper.map(careerManager, CareerManager.class);
		return this.modelMapper.map(this.careerManagerRepository.save(careerManagerEntity), CareerManagerResponse.class);
	}

	@Override
	public CareerManagerResponse updateClient(Long managerId, CareerManagerRequest careerManager) {
		Optional<CareerManager> careerManagerEntity = careerManagerRepository.findById(managerId);
		if(careerManagerEntity.isPresent()) {
			CareerManager careerManagerUpdate = modelMapper.map(careerManager, CareerManager.class);
			careerManagerUpdate.setUsers(careerManagerEntity.get().getUsers());
			careerManagerUpdate.setManagerId(careerManagerEntity.get().getManagerId());
			return modelMapper.map(this.careerManagerRepository.save(careerManagerUpdate), CareerManagerResponse.class);
		} else {
			throw new ResourceNotFound();
		}
	}

	@Override
	public CareerManagerResponse findById(Long managerId) {
		Optional<CareerManager> careerManagerOptional = this.careerManagerRepository.findById(managerId);
		if(careerManagerOptional.isPresent()) {
			return modelMapper.map(careerManagerOptional.get(), CareerManagerResponse.class);
		} else {
			throw new ResourceNotFound();
		}
	}

	@Override
	public Optional<CareerManager> findByMobileNumber(String mobile) {
		return this.careerManagerRepository.findByPhoneNumber(mobile);
	}

	@Override
	public Optional<CareerManager> findByManagerId(Long managerId) {
		return this.careerManagerRepository.findById(managerId);
	}

	@Override
	public CareerManager create(CareerManagerRequest careerManagerRequest) {
		CareerManager careerManagerEntity = modelMapper.map(careerManagerRequest, CareerManager.class);
		return this.careerManagerRepository.save(careerManagerEntity);
	}

	@Override
	public List<CareerManager> findAll() {
		return this.careerManagerRepository.findAll();
	}

	@Override
	public void deleteById(Long managerId) {
		this.careerManagerRepository.deleteById(managerId);
	}

	@Override
	public void deleteByPhoneNumber(String phoneNumber) {
		Optional<CareerManager> opManager = findByMobileNumber(phoneNumber);
		if(opManager.isPresent()) {
			this.careerManagerRepository.delete(opManager.get());
		}
	}

}
