package com.retailhome.controllers;

import com.retailhome.dtos.AccountDTO;
import com.retailhome.repositories.AccountRepository;
import com.retailhome.repositories.ClientRepository;
import com.retailhome.service.AccountService;
import com.retailhome.service.UtilService;
import com.retailhome.utils.NumberAccountRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Set;
@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;
    private final UtilService utilService;

    public AccountController(AccountService accountService, UtilService utilService) {
        this.utilService = utilService;
        this.accountService = accountService;
    }

    @GetMapping(path = "/accounts")
    public ResponseEntity<?> getAccounts(){
       return this.accountService.findAll();
    }

   @GetMapping(path = "/accounts/{id}")
    public ResponseEntity<?> getAccount(@Valid  @PathVariable Long id, BindingResult result ){
       if (result.hasErrors()){
           return new ResponseEntity<>( this.utilService.errorResult(result) ,HttpStatus.BAD_REQUEST );
       }
        return this.accountService.findById(id);
    }

    @PostMapping(path = "/clients/accounts")
    public ResponseEntity<?> register(@Valid @RequestBody AccountDTO accountDto, BindingResult result ) {
        if (result.hasErrors()){
            return new ResponseEntity<>( this.utilService.errorResult(result) ,HttpStatus.BAD_REQUEST );
        }
        return this.accountService.save(accountDto);
    }

   @PutMapping(path = "/clients/current/accounts")
    public ResponseEntity<?> update(@Valid  @RequestBody AccountDTO accountDto, BindingResult result ) {
       if (result .hasErrors()){
           return new ResponseEntity<>( this.utilService.errorResult(result) ,HttpStatus.BAD_REQUEST );
       }
       return this.accountService.update(accountDto);
   }

   @DeleteMapping(path= "/clients/accounts")
   public ResponseEntity<?> delete(@Valid @PathVariable AccountDTO  accountDTO ,BindingResult result  ){
       if (result .hasErrors()){
           return new ResponseEntity<>( this.utilService.errorResult(result) ,HttpStatus.BAD_REQUEST );
       }
       return this.accountService.delete(accountDTO);
   }
}