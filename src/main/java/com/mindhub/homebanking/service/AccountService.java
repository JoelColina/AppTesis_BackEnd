package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.AccountDTO;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public interface AccountService {
    Set<AccountDTO> findAll();

    AccountDTO findById(Long id);
}
