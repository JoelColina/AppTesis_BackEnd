package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.CreditsHeaderDTO;

import java.util.Set;

public interface CreditsHeaderService {

    Set<CreditsHeaderDTO> finAll();

    CreditsHeaderDTO finById(Long id);
}
