package com.mindhub.homebanking.service;


import com.mindhub.homebanking.dtos.PurchasingDetailDTO;
import com.mindhub.homebanking.dtos.PurchasingHeaderDTO;
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
