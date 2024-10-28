package com.retailhome.service;

import com.retailhome.dtos.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface AccountService {
    ResponseEntity<?> findAll();


    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> update(AccountDTO accountDTO);

    ResponseEntity<?> delete (AccountDTO accountDTO);

    ResponseEntity<?>  save (AccountDTO accountDTO);
}
