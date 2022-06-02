package com.ubqt.service;

import java.util.Set;

import com.ubqt.model.ShortListRequest;

public interface ShortListService {

	void save(ShortListRequest shortList);

	Set<Long> getShortListedUsersFor(Long clientId);

	void delete(Long id);

	void delete(Long clientId, Long userId);

}
