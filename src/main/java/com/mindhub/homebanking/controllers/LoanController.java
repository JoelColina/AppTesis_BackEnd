package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @RequestMapping("/loans")
    public List<LoanDTO> getLoans() {
        return this.loanService.findAll();
    }

    @RequestMapping("/loans/{id}")
    public LoanDTO getLoans(@PathVariable Long id){
        return this.loanService.findById(id);
    }

    @Transactional
    @RequestMapping(path = "/loans",method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody LoanApplicationDTO loanApplicationDTO,
                                           Authentication authentication){
        return this.loanService.newRegister(loanApplicationDTO, authentication );
    }
}