package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.CreditsHeaderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CreditsHeaderService {

    Set<CreditsHeaderDTO> finAll();

    CreditsHeaderDTO findById(Long id);

    // agregar
    ResponseEntity<?> save (CreditsHeaderDTO creditsHeaderDTO);

    // eliminar
    boolean delete (CreditsHeaderDTO creditsHeaderDTO);

    // actualizar
    ResponseEntity<?> update(CreditsHeaderDTO creditsHeaderDTO);
}
