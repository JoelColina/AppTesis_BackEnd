package com.retailhome.service;

import com.retailhome.dtos.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface TransactionService {
   Set<TransactionDTO> findAll();

   TransactionDTO findById(Long id);

   ResponseEntity<Object> newTransaction(Integer amount, String description, String fromAccount, String toAccount, Authentication authentication);

}
