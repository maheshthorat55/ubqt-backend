package com.ubqt.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubqt.entity.Template;
import com.ubqt.repository.TemplateRepository;

public class TemplateServiceImpl implements TemplateService {

	@Autowired
	private TemplateRepository templateRepository;
	
	@Override
	public Template getDefaultTemplate() {
		return templateRepository.getByDefaultTemplate(1);
	}

}
