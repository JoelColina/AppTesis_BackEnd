package com.mindhub.homebanking.service;


import com.mindhub.homebanking.dtos.PurchasingDetailDTO;

import java.util.Set;

public interface PurchasingDetailService {

    Set<PurchasingDetailDTO> finAll();

    PurchasingDetailDTO finById(Long id);
}
