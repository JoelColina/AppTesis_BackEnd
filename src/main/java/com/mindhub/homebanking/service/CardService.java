package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.CardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CardService {
    Set<CardDTO> finAll();

    CardDTO finById(Long id);

}
