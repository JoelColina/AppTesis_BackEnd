package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.utils.NumberAccountRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/accounts")
    public Set<AccountDTO> getAccounts(){
       return this.accountService.findAll();
    }

   @RequestMapping("/accounts/{id}")
    public AccountDTO getAccounts(@PathVariable Long id){
        return this.accountService.findById(id);
    }


    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> register( Authentication authentication ) {

        int balance = 0;
        LocalDate createDate = LocalDate.now();

        Client client= clientRepository.findByEmail(authentication.getName());

//       if (client.getAccounts().size() == 3){
//           return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
//       }

        String newAccount;
        newAccount = NumberAccountRandom.getRandomNumber();

        accountRepository.save(new Account(newAccount, createDate, balance, client));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//   @RequestMapping(path = "/clients/current/accounts")
//   public List<AccountDTO> getCurrentAccounts(Authentication authentication){
//         Client client = clientRepository.findByEmail(authentication.getName());
//         return client.getAccounts().stream().map(AccountDTO::new).collect(toList());
//   }

}
