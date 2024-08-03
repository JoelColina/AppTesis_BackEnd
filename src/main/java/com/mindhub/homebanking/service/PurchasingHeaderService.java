package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.CreditsHeaderDTO;
import com.mindhub.homebanking.dtos.PurchasingHeaderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PurchasingHeaderService {

    Set<PurchasingHeaderDTO> finAll();

    PurchasingHeaderDTO finById(Long id);

    // agregar
    PurchasingHeaderDTO save (PurchasingHeaderDTO purchasingHeaderDTO);

    // eliminar
    boolean delete (PurchasingHeaderDTO purchasingHeaderDTO);

    // actualizar
    ResponseEntity<?> update(PurchasingHeaderDTO purchasingHeaderDTO);
}
