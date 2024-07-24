package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.CreditDetailDTO;

import java.util.Set;

public interface CreditDetailService {

    Set<CreditDetailDTO> finAll();

    CreditDetailDTO finById(Long id);
}
