package com.ubqt.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubqt.entity.ShortList;
import com.ubqt.model.ShortListRequest;
import com.ubqt.repository.ShortListRepository;

@Service
public class ShortListServiceImpl implements ShortListService {

	@Autowired
	private ShortListRepository shortListRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public void save(ShortListRequest shortListRequest) {
		ShortList shortList = shortListRepository.findByUserIdAndShortListId(shortListRequest.getUserId(), shortListRequest.getShortListId());
		if(shortList == null) {
			this.shortListRepository.save(this.modelMapper.map(shortListRequest, ShortList.class));
		}
	}

	@Override
	public Set<Long> getShortListedUsersFor(Long userId) {
		return this.shortListRepository.findAllByUserId(userId).stream().map(s -> s.getShortListId())
				.collect(Collectors.toSet());
	}

	@Override
	public void delete(Long id) {
		this.shortListRepository.deleteById(id);
	}

	@Override
	public void delete(Long userId, Long shortListId) {
		ShortList shortList = shortListRepository.findByUserIdAndShortListId(userId, shortListId);
		if(shortList != null) {
			this.shortListRepository.delete(shortList);
		}
	}

}
