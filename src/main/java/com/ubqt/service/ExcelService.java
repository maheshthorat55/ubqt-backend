package com.ubqt.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ubqt.entity.CareerManager;
import com.ubqt.entity.User;
import com.ubqt.model.CareerManagerRequest;
import com.ubqt.model.UserManagerMap;
import com.ubqt.model.UserRequest;
import com.ubqt.util.ExcelHelper;

@Service
public class ExcelService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CareerManagerService careerManagerServicer;

	public void save(MultipartFile file) throws IOException {
		List<UserManagerMap> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
		User userEntity = null;
		CareerManager careerManagerEntity = null;
		for (UserManagerMap userManagerMap : tutorials) {
			Optional<User> user = userService.findByMobileNumber(userManagerMap.getPhoneUser() + "");
			Optional<CareerManager> careerManager = careerManagerServicer.findByMobileNumber(userManagerMap.getPhoneCareerManager() + "");
			
			if(user.isPresent()) {
				userEntity = user.get();
			} else {
				UserRequest userRequest = new UserRequest();
				userRequest.setMobileNumber(userManagerMap.getPhoneUser()+"");
				userEntity = userService.create(userRequest);
			}
			if(careerManager.isPresent()) {
				careerManagerEntity = careerManager.get();
			} else {
				CareerManagerRequest careerManagerRequest = new CareerManagerRequest();
				careerManagerRequest.setPhoneNumber(userManagerMap.getPhoneCareerManager()+"");
				careerManagerEntity = careerManagerServicer.create(careerManagerRequest);
			}
		
			userEntity.setCareerManager(careerManagerEntity);
			
			userService.saveToRepository(userEntity);
		}
	}
}