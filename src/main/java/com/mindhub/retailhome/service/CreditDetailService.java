package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.CreditDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CreditDetailService {

    Set<CreditDetailDTO> finAll();

    CreditDetailDTO findById(Long id);

    // agregar
    ResponseEntity<?> save (CreditDetailDTO creditDetailDTO);

    // actualizar
    ResponseEntity<?> update(CreditDetailDTO creditDetailDTO);
}
