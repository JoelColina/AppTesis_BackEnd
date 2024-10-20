package com.retailhome.controllers;

import com.retailhome.dtos.PurchasingDetailDTO;
import com.retailhome.service.PurchasingDetailService;
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
    public Set<PurchasingDetailDTO> getpurchasingdetails(){
        return this.purchasingdetailservice.finAll();
    }

    @RequestMapping("/purchasingdetails/{id}")
    public PurchasingDetailDTO getpurchasingdetails(@PathVariable long id){
        return this.purchasingdetailservice.findById(id);
    }

}
