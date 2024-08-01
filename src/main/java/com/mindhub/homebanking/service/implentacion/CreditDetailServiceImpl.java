package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.CreditDetailDTO;
import com.mindhub.homebanking.service.CreditDetailService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CreditDetailServiceImpl implements CreditDetailService {

    @Override
    public Set<CreditDetailDTO> finAll() {
        return Set.of();
    }

    @Override
    public CreditDetailDTO finById(Long id) {
        return null;
    }
}
