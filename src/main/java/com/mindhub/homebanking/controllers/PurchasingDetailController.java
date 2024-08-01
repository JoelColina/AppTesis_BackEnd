package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.PurchasingDetailDTO;
import com.mindhub.homebanking.repositories.PurchasingDetailRepository;
import com.mindhub.homebanking.service.PurchasingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class PurchasingDetailController {

    private final PurchasingDetailService purchasingdetailservice;

    public PurchasingDetailController(PurchasingDetailService purchasingdetailService) {
        this.purchasingdetailservice = purchasingdetailService;
    }

    @RequestMapping("/purchasingdetails")
    public Set<PurchasingDetailDTO> getcard(){
        return this.purchasingdetailservice.finAll();
    }

    @RequestMapping("/purchasingdetails/{id}")
    public PurchasingDetailDTO getcard(@PathVariable long id){
        return this.purchasingdetailservice.finById(id);
    }

}
