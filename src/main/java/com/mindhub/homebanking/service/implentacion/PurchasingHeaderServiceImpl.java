package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.PurchasingHeaderDTO;
import com.mindhub.homebanking.repositories.PurchasingHeaderRepository;
import com.mindhub.homebanking.service.PurchasingHeaderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class PurchasingHeaderServiceImpl implements PurchasingHeaderService {

    @Autowired
    private PurchasingHeaderRepository purchasingHeaderRepository;

    @Override
    public Set<PurchasingHeaderDTO> finAll() {
        return Set.of();
    }

    @Override
    public PurchasingHeaderDTO finById(Long id) {
        return null;
    }
}
