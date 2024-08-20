package com.mindhub.retailhome.controllers;

import com.mindhub.retailhome.dtos.CreditsHeaderDTO;
import com.mindhub.retailhome.repositories.CreditsHeaderRepository;
import com.mindhub.retailhome.service.CreditsHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class CreditsHeaderController {

    private final CreditsHeaderService creditsheaderservice;

    @Autowired
    private CreditsHeaderRepository creditsheaderrepository;

    public CreditsHeaderController(CreditsHeaderService creditsheaderservice) {
        this.creditsheaderservice = creditsheaderservice;
    }

    @RequestMapping("/creditsheaders")
    public Set<CreditsHeaderDTO> getcreditsheaders(){
        return this.creditsheaderservice.finAll();
    }

    @RequestMapping("/creditsheaders/{id}")
    public CreditsHeaderDTO getcreditsheaders(@PathVariable long id){
        return this.creditsheaderservice.findById(id);
    }

}
