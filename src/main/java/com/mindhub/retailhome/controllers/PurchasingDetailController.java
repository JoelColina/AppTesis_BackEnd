package com.mindhub.retailhome.controllers;

import com.mindhub.retailhome.dtos.PurchasingDetailDTO;
import com.mindhub.retailhome.service.PurchasingDetailService;
import com.mindhub.retailhome.service.UtilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class PurchasingDetailController {

    private final PurchasingDetailService purchasingdetailservice;
    private final UtilService utilService;

    public PurchasingDetailController(PurchasingDetailService purchasingdetailService, UtilService utilService) {
        this.purchasingdetailservice = purchasingdetailService;
        this.utilService = utilService;
    }

    @GetMapping(path = "/purchasingdetails")
    public ResponseEntity<?> getpurchasingdetails(){
        return this.purchasingdetailservice.finAll();
    }

    @PostMapping(path = "/purchasingdetails/{id}")
    public ResponseEntity<?>getpurchasingdetails(@Valid @PathVariable Long id, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(this.utilService.errorResult(result), HttpStatus.BAD_REQUEST);
        }
        return this.purchasingdetailservice.findById(id);
    }

}
