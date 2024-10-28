package com.retailhome.service;

import com.retailhome.dtos.CreditDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CreditDetailService {

     Set<CreditDetailDTO> finAllDetails() ;


     CreditDetailDTO findByIdDetail(Long id);

    // agregar
    CreditDetailDTO saveDetail (CreditDetailDTO creditDetailDTO);

    // actualizar
    CreditDetailDTO updateD(CreditDetailDTO creditDetailDTO);

    // eliminar
    boolean deleteDetail(CreditDetailDTO creditDetailDTO);

}
