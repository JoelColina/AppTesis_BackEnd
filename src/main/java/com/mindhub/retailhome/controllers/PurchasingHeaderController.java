package com.mindhub.retailhome.controllers;

import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import com.mindhub.retailhome.repositories.PurchasingHeaderRepository;
import com.mindhub.retailhome.service.PurchasingHeaderService;
import com.mindhub.retailhome.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class PurchasingHeaderController {

    private final PurchasingHeaderService purchasingHeaderService;
    private final UtilService utilService;

    public PurchasingHeaderController(PurchasingHeaderService shoppingService, UtilService utilService) {
        this.purchasingHeaderService = shoppingService;
        this.utilService = utilService;
    }

    @GetMapping(path = "/purchasingheaders")
    public ResponseEntity<?> getpurchasingheader(){
        return this.purchasingHeaderService.findAll();
    }

    @PostMapping(path = "/purchasingheaders/{id}")
    public ResponseEntity<?> getpurchasingheaders(@Valid @PathVariable Long id, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(this.utilService.errorResult(result), HttpStatus.BAD_REQUEST);
        }
        return this.purchasingHeaderService.findById(id);
    }
}
