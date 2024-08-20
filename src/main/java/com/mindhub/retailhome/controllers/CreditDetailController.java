package com.mindhub.retailhome.controllers;

import com.mindhub.retailhome.dtos.CreditDetailDTO;
import com.mindhub.retailhome.repositories.CreditDetailRepository;
import com.mindhub.retailhome.service.CreditDetailService;
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
