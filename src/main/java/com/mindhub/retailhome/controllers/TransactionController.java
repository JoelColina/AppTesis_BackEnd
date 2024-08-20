package com.mindhub.retailhome.controllers;

import com.mindhub.retailhome.dtos.TransactionDTO;
import com.mindhub.retailhome.repositories.AccountRepository;
import com.mindhub.retailhome.repositories.TransactionRepository;
import com.mindhub.retailhome.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionController(TransactionService transactionService,
                                 AccountRepository accountRepository) {

        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
    }

    @RequestMapping("/transactions")
    public Set<TransactionDTO> getTransactions() {
        return transactionService.findAll();
    }

    @RequestMapping("/transactions/{id}")
    public TransactionDTO getTransactions(@PathVariable Long id){
        return this.transactionService.findById(id);
    }

    @Transactional
    @RequestMapping(path = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestParam String fromAccountNumber,
                                           @RequestParam String toAccountNumber,
                                           @RequestParam Integer amount,
                                           @RequestParam String description,
                                            Authentication authentication ) {

        return this.transactionService.newTransaction(amount, description, fromAccountNumber, toAccountNumber, authentication);

    }
}
