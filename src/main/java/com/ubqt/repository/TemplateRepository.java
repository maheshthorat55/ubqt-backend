package com.ubqt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubqt.entity.Template;

public interface TemplateRepository extends JpaRepository<Template, Long> {

	Template getByDefaultTemplate(int i);

}
