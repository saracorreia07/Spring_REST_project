package com.algaworks.osworks.domain.service;

import com.algaworks.osworks.domain.exception.BusinessException;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoginService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) {

        Client existingClient = clientRepository.findByEmail(client.getEmail());

        if(existingClient != null && !existingClient.equals(client)) {
            throw new BusinessException("It already exists a client with that email.");
        }

        return clientRepository.save(client);
    }

    public void exclude(Long clientId){
        clientRepository.deleteById(clientId);
    }
}
