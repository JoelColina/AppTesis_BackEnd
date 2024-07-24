package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.PurchasingHeaderDTO;

import java.util.Set;

public interface PurchasingHeaderService {

    Set<PurchasingHeaderDTO> finAll();

    PurchasingHeaderDTO finById(Long id);

}
