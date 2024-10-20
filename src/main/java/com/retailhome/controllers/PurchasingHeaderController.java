package com.retailhome.controllers;

import com.retailhome.dtos.PurchasingHeaderDTO;
import com.retailhome.repositories.PurchasingHeaderRepository;
import com.retailhome.service.PurchasingHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class PurchasingHeaderController {

    private final PurchasingHeaderService purchasingHeaderService;

    @Autowired
    private PurchasingHeaderRepository shoppingRepository;

    public PurchasingHeaderController(PurchasingHeaderService shoppingService) {
        this.purchasingHeaderService = shoppingService;
    }

    @RequestMapping("/purchasingheaders")
    public Set<PurchasingHeaderDTO> getpurchasingheaders(){
        return this.purchasingHeaderService.finAll();
    }

    @RequestMapping("/purchasingheaders/{id}")
    public PurchasingHeaderDTO getpurchasingheaders(@PathVariable long id){
        return this.purchasingHeaderService.findById(id);
    }
}
