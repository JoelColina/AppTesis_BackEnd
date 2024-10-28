package com.mindhub.retailhome.controllers;

import com.mindhub.retailhome.dtos.LoanApplicationDTO;
import com.mindhub.retailhome.dtos.LoanDTO;
import com.mindhub.retailhome.service.LoanService;
import com.mindhub.retailhome.service.UtilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {

    private final LoanService loanService;
    private final UtilService utilService;

    public LoanController(LoanService loanService, UtilService utilService) {
        this.loanService = loanService;
        this.utilService = utilService;
    }

    @GetMapping(path = "/loans")
    public ResponseEntity<?> getLoan() {
        return this.loanService.findAll();
    }

    @PostMapping(path = "/loans/{id}")
    public ResponseEntity<?> getLoans(@Valid @PathVariable Long id, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(this.utilService.errorResult(result), HttpStatus.BAD_REQUEST);
        }
        return this.loanService.findById(id);
    }

    @Transactional
    @RequestMapping(path = "/loans",method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody LoanApplicationDTO loanApplicationDTO,
                                           Authentication authentication){
        return this.loanService.newRegister(loanApplicationDTO, authentication );
    }
}