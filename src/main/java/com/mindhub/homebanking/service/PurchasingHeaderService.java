package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.PurchasingHeaderDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PurchasingHeaderService {

    Set<PurchasingHeaderDTO> finAll();

    PurchasingHeaderDTO finById(Long id);

}
