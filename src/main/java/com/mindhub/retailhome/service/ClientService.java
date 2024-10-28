package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.ClientDTO;
import com.mindhub.retailhome.models.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ClientService {

    ClientDTO findByEmail(String email);
    ResponseEntity<?> findAll();
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> save(ClientDTO clientDTO);
    ResponseEntity<?> update(ClientDTO clientDTO);
    ResponseEntity<?> delete(ClientDTO clientDTO);
}
