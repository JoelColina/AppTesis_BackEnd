package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.CreditsHeaderDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CreditsHeaderService {

    Set<CreditsHeaderDTO> finAll();

    CreditsHeaderDTO finById(Long id);
}
