package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.CreditDetailDTO;
import com.mindhub.homebanking.dtos.CreditsHeaderDTO;
import com.mindhub.homebanking.repositories.CreditDetailRepository;
import com.mindhub.homebanking.repositories.CreditsHeaderRepository;
import com.mindhub.homebanking.service.CreditDetailService;
import com.mindhub.homebanking.service.CreditsHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

public class CreditDetailController {

    private final CreditDetailService creditsdetailservice;

    @Autowired
    private CreditDetailRepository creditsdetailrepository;

    public CreditDetailController(CreditDetailService creditsdetailservice) {
        this.creditsdetailservice = creditsdetailservice;
    }

    @RequestMapping("/shopping")
    public Set<CreditDetailDTO> getcard(){
        return this.creditsdetailservice.finAll();
    }

    @RequestMapping("/shopping/{id}")
    public CreditDetailDTO getcard(@PathVariable long id){
        return this.creditsdetailservice.finById(id);
    }

}
