package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public interface AccountService {
    Set<AccountDTO> findAll();

    AccountDTO findById(Long id);

    ResponseEntity<?> update(AccountDTO accountDTO);

    boolean delete (AccountDTO accountDTO);

    ResponseEntity<?>  save (AccountDTO accountDTO);
}
