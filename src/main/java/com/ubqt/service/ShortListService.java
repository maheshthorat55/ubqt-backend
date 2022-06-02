package com.ubqt.service;

import java.util.Set;

import com.ubqt.model.ShortListRequest;

public interface ShortListService {

	void save(ShortListRequest user);

	Set<Long> getShortListedUsersFor(Long userId);

	void delete(Long id);

	void delete(Long userId, Long shortListId);

}
