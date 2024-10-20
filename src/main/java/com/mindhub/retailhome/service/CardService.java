package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.AddressesDTO;
import com.mindhub.retailhome.dtos.CardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CardService {
    Set<CardDTO> finAll();
    CardDTO findById(Long id);
//    List<CardDTO> findCardByClient(String idClient);
    // agregar
    ResponseEntity<?>  save (CardDTO cardDTO);
    // eliminar
    boolean delete (CardDTO cardDTO);
    // actualizar
    ResponseEntity<?> update(CardDTO cardDTO);

}
