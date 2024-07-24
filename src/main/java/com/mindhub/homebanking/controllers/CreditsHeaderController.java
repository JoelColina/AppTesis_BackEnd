package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.CreditsHeaderDTO;
import com.mindhub.homebanking.dtos.PurchasingDetailDTO;
import com.mindhub.homebanking.repositories.CreditsHeaderRepository;
import com.mindhub.homebanking.repositories.PurchasingDetailRepository;
import com.mindhub.homebanking.service.CreditsHeaderService;
import com.mindhub.homebanking.service.PurchasingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

public class CreditsHeaderController {

    private final CreditsHeaderService creditsheaderservice;

    @Autowired
    private CreditsHeaderRepository creditsheaderrepository;

    public CreditsHeaderController(CreditsHeaderService creditsheaderservice) {
        this.creditsheaderservice = creditsheaderservice;
    }

    @RequestMapping("/shopping")
    public Set<CreditsHeaderDTO> getcard(){
        return this.creditsheaderservice.finAll();
    }

    @RequestMapping("/shopping/{id}")
    public CreditsHeaderDTO getcard(@PathVariable long id){
        return this.creditsheaderservice.finById(id);
    }

}
