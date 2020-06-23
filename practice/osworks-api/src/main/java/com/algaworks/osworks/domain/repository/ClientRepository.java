package com.algaworks.osworks.domain.repository;

import com.algaworks.osworks.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByName(String name);
    List<Client> findByNameContains(String name);
    Client findByEmail(String email);
}
