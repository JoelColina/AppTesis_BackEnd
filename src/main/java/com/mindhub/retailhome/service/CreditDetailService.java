package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.CreditDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CreditDetailService {

    ResponseEntity<?>  finAll();

    ResponseEntity<?>  findById(Long id);

    // agregar
    ResponseEntity<?> save (CreditDetailDTO creditDetailDTO);

    // actualizar
    ResponseEntity<?> update(CreditDetailDTO creditDetailDTO);
}
