package com.ubqt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubqt.entity.CareerManager;

public interface CareerManagerRepository extends JpaRepository<CareerManager, Long>{

	Optional<CareerManager> findByPhoneNumber(String mobile);

}
