package com.ubqt.service;

import java.util.Optional;

import javax.validation.Valid;

import com.ubqt.entity.Client;
import com.ubqt.model.ClientRequest;
import com.ubqt.model.ClientResponse;

public interface ClientService {

	ClientResponse save(ClientRequest client);

	ClientResponse updateClient(Long clientId, @Valid ClientRequest client);

	ClientResponse findById(Long clientId);

	Optional<Client> findByMobileNumber(String mobile);

}
