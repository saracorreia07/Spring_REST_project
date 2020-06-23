package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import com.algaworks.osworks.domain.repository.ClientRepository;
import com.algaworks.osworks.domain.service.ClientLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algaworks.osworks.domain.model.Client;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientLoginService clientLogin;

	@GetMapping
	public List<Client> list() {
		return clientRepository.findAll();
	}

	@GetMapping("/{clientId}")
	public ResponseEntity<Client> get(@PathVariable Long clientId){

		Optional<Client> client = clientRepository.findById(clientId);

		if(client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client add(@Valid @RequestBody Client client) {
		return clientLogin.save(client);
	}

	@PutMapping("/{clientId}")
	public ResponseEntity<Client> update(@Valid @PathVariable Long clientId, @RequestBody Client client){

		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}

		client.setId(clientId);
		client = clientLogin.save(client);

		return ResponseEntity.ok(client);
	}

	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> delete(@PathVariable Long clientId){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}

		clientLogin.exclude(clientId);

		return ResponseEntity.noContent().build();
	}

}
