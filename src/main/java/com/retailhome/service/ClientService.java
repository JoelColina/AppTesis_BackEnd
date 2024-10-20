package com.retailhome.service;

import com.retailhome.dtos.ClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ClientService {

    ClientDTO findByEmail(String email);
    Set<ClientDTO> findAll();
    ClientDTO finById (Long id);
    ResponseEntity<?> save(ClientDTO clientDTO);
    ResponseEntity<?> update(ClientDTO clientDTO);
    ResponseEntity<?> delete(ClientDTO clientDTO);
}
