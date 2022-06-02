package com.ubqt.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubqt.entity.ShortList;

public interface ShortListRepository extends JpaRepository<ShortList, Long> {

	Set<ShortList> findAllByClientId(Long clientId);

	ShortList findByClientIdAndUserId(Long clientId, Long userId);

}
