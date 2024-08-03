package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.CardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CardService {
    Set<CardDTO> finAll();

    CardDTO finById(Long id);

    // agregar
    CardDTO save (CardDTO cardDTO);

    // eliminar
    boolean delete (CardDTO cardDTO);

    // actualizar
    ResponseEntity<?> update(CardDTO cardDTO);


}
