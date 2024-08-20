package com.mindhub.retailhome.service;


import com.mindhub.retailhome.dtos.PurchasingDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PurchasingDetailService {

    Set<PurchasingDetailDTO> finAll();

    PurchasingDetailDTO findById(Long id);

    // agregar
    ResponseEntity<?> save (PurchasingDetailDTO purchasingDetailDTO);

    // actualizar
    ResponseEntity<?> update(PurchasingDetailDTO purchasingDetailDTO);
}
