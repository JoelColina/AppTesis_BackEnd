package com.retailhome.service;

import com.retailhome.dtos.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface AccountService {
    ResponseEntity<?> findAll();

    AccountDTO findById(Long id);

    ResponseEntity<?> update(AccountDTO accountDTO);

    boolean delete (AccountDTO accountDTO);

    ResponseEntity<?>  save (AccountDTO accountDTO);
}
