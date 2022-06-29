package com.ubqt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubqt.entity.Client;
import com.ubqt.model.ClientRequest;
import com.ubqt.model.ClientResponse;
import com.ubqt.service.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping
	public ResponseEntity<ClientResponse> createUser(@Valid @RequestBody ClientRequest client){
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<ClientResponse> updateUser(@PathVariable Long clientId, @Valid @RequestBody ClientRequest client){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clientService.updateClient(clientId, client));
	}

	@GetMapping("/{clientId}")
	public ResponseEntity<ClientResponse> getClient(@PathVariable Long clientId){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(clientId));
	}
	
	@DeleteMapping("/{phoneNumber}")
	public ResponseEntity<Void> deleteClient(@PathVariable String phoneNumber){
		clientService.deleteByPhoneNumber(phoneNumber);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping()
	public ResponseEntity<List<Client>> getAllClients(){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
	}

}
