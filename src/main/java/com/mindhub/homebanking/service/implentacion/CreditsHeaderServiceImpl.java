package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.CreditsHeaderDTO;
import com.mindhub.homebanking.service.CreditsHeaderService;

import java.util.Set;

public class CreditsHeaderServiceImpl implements CreditsHeaderService {
    @Override
    public Set<CreditsHeaderDTO> finAll() {
        return Set.of();
    }

    @Override
    public CreditsHeaderDTO finById(Long id) {
        return null;
    }
}
