package com.mindhub.homebanking.service;


import com.mindhub.homebanking.dtos.PurchasingDetailDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PurchasingDetailService {

    Set<PurchasingDetailDTO> finAll();

    PurchasingDetailDTO finById(Long id);
}
