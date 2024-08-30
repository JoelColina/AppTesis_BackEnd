package com.mindhub.retailhome.repositories;

import com.mindhub.retailhome.dtos.ClientDTO;
import com.mindhub.retailhome.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    ClientDTO findByEmail(String inputName);
    ClientDTO findByIdClient(String idClient);
}

