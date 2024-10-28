package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public interface AccountService {
    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> update(AccountDTO accountDTO);

    ResponseEntity<?>  delete(AccountDTO accountDTO);

    ResponseEntity<?> save(AccountDTO accountDTO);
}
