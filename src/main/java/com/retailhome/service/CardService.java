package com.retailhome.service;

import com.retailhome.dtos.CardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CardService {
    Set<CardDTO> finAll();

    CardDTO findById(Long id);
    // agregar
    ResponseEntity<?>  save (CardDTO cardDTO);
    // eliminar
    boolean delete (CardDTO cardDTO);
    // actualizar
    ResponseEntity<?> update(CardDTO cardDTO);

}
