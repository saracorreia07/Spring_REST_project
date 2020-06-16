package com.algaworks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Client;

@RestController
public class ClientController {

	@GetMapping("/clients")
	public List<Client> list() {
		Client client1 = new Client();
		client1.setId(1L);
		client1.setName("John");
		client1.setTelephone("938522222");
		client1.setEmail("jonhdoe@mail.com");
		
		Client client2 = new Client();
		client2.setId(2L);
		client2.setName("Mary");
		client2.setTelephone("932337777");
		client2.setEmail("marydoe@mail.com");
		
		return Arrays.asList(client1, client2);
	}
	
}
