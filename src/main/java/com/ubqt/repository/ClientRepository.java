package com.ubqt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubqt.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	Optional<Client> findByPhoneNumber(String mobile);

}
