package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.PurchasingHeaderDTO;
import com.mindhub.homebanking.repositories.PurchasingHeaderRepository;
import com.mindhub.homebanking.service.PurchasingHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
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

    @Override
    public PurchasingHeaderDTO save(PurchasingHeaderDTO purchasingHeaderDTO) {
        return null;
    }

    @Override
    public boolean delete(PurchasingHeaderDTO purchasingHeaderDTO) {
        return false;
    }

    @Override
    public ResponseEntity<?> update(PurchasingHeaderDTO purchasingHeaderDTO) {
        return null;
    }
}
