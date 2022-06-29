package com.ubqt.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubqt.entity.Client;
import com.ubqt.exception.ResourceNotFound;
import com.ubqt.model.ClientRequest;
import com.ubqt.model.ClientResponse;
import com.ubqt.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ClientResponse save(ClientRequest client) {
		Client clientEntity = modelMapper.map(client, Client.class);
		return this.modelMapper.map(this.clientRepository.save(clientEntity), ClientResponse.class);
	}

	@Override
	public ClientResponse updateClient(Long clientId, @Valid ClientRequest client) {
		Optional<Client> clientEntity = clientRepository.findById(clientId);
		if(clientEntity.isPresent()) {
			Client clientUpdate = modelMapper.map(client, Client.class);
			clientUpdate.setClientId(clientEntity.get().getClientId());
			return modelMapper.map(clientRepository.save(clientUpdate), ClientResponse.class);
		} else {
			throw new ResourceNotFound();
		}
	}

	@Override
	public ClientResponse findById(Long clientId) {
		Optional<Client> clientOptional = this.clientRepository.findById(clientId);
		if(clientOptional.isPresent()) {
			return modelMapper.map(clientOptional.get(), ClientResponse.class);
		} else {
			throw new ResourceNotFound();
		}
	}

	@Override
	public Optional<Client> findByMobileNumber(String mobile) {
		return this.clientRepository.findByPhoneNumber(mobile);
	}

	@Override
	public List<Client> findAll() {
		return this.clientRepository.findAll();
	}

	@Override
	public void deleteById(Long clientId) {
		this.clientRepository.deleteById(clientId);
	}

	@Override
	public void deleteByPhoneNumber(String phoneNumber) {
		Optional<Client> oClient = findByMobileNumber(phoneNumber);
		if(oClient.isPresent()) {
			this.clientRepository.delete(oClient.get());
		}
	}

}
