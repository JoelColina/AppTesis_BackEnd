package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PurchasingHeaderService {

    Set<PurchasingHeaderDTO> finAll();

    PurchasingHeaderDTO findById(Long id);
    // agregar
    ResponseEntity<?>  save (PurchasingHeaderDTO purchasingHeaderDTO);
    // eliminar
    boolean delete (PurchasingHeaderDTO purchasingHeaderDTO);
    // actualizar
    ResponseEntity<?> update(PurchasingHeaderDTO purchasingHeaderDTO);
}
