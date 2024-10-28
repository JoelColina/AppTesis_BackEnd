package com.retailhome.service;

import com.retailhome.dtos.CreditsHeaderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CreditsHeaderService {

    ResponseEntity<?> finAll();

    ResponseEntity<?>  findById(Long id);

    // agregar
    ResponseEntity<?> save (CreditsHeaderDTO creditsHeaderDTO);

    // eliminar
    ResponseEntity<?> delete (CreditsHeaderDTO creditsHeaderDTO);

    // actualizar
    ResponseEntity<?> update(CreditsHeaderDTO creditsHeaderDTO);
}
