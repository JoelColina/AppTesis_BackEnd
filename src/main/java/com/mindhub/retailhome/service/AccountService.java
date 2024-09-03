package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public interface AccountService {
    Set<AccountDTO> findAll();
    AccountDTO findById(Long id);
    List<AccountDTO> findAccountByClient(String idClient);
    ResponseEntity<?> update(AccountDTO accountDTO);
    boolean delete (AccountDTO accountDTO);
    ResponseEntity<?>  save (AccountDTO accountDTO);
}
