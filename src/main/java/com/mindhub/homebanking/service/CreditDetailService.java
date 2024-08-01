package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.CreditDetailDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CreditDetailService {

    Set<CreditDetailDTO> finAll();

    CreditDetailDTO finById(Long id);
}
