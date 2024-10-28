package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.AddressesDTO;
import com.mindhub.retailhome.dtos.CreditsHeaderDTO;
import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CreditsHeaderService {

    ResponseEntity<?>  finAll();

    ResponseEntity<?> findById(Long id);

    // agregar
    ResponseEntity<?>  save (CreditsHeaderDTO creditsHeaderDTO);

    // eliminar
    ResponseEntity<?> delete (CreditsHeaderDTO creditsHeaderDTO);

    // actualizar
    ResponseEntity<?> update(CreditsHeaderDTO creditsHeaderDTO);
}
