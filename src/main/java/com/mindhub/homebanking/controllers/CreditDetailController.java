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
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class CreditDetailController {

    private final CreditDetailService creditsdetailservice;

    @Autowired
    private CreditDetailRepository creditsdetailrepository;

    public CreditDetailController(CreditDetailService creditsdetailservice) {
        this.creditsdetailservice = creditsdetailservice;
    }

    @RequestMapping("/creditsdetails")
    public Set<CreditDetailDTO> getcreditsdetails(){
        return this.creditsdetailservice.finAll();
    }

    @RequestMapping("/creditsdetails/{id}")
    public CreditDetailDTO getcreditsdetails(@PathVariable long id){
        return this.creditsdetailservice.findById(id);
    }

}
