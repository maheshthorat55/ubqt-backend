package com.ubqt.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubqt.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);

	Optional<User> findByLinkdinId(String email);

	Optional<User> findByMobileNumber(String mobileNumber);

	List<User> findAllByUserIdIn(Set<Long> userIds);

}
