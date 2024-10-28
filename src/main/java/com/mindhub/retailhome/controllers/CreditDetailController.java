package com.mindhub.retailhome.controllers;

import com.mindhub.retailhome.dtos.CreditDetailDTO;
import com.mindhub.retailhome.repositories.CreditDetailRepository;
import com.mindhub.retailhome.service.CreditDetailService;
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
public class CreditDetailController {

    private final CreditDetailService creditsdetailservice;
    private final UtilService utilService;

    public CreditDetailController(CreditDetailService creditsdetailservice, UtilService utilService) {
        this.creditsdetailservice = creditsdetailservice;
        this.utilService = utilService;
    }

    @GetMapping(path = "/creditsdetails")
    public ResponseEntity<?> getcreditsdetails(){
        return this.creditsdetailservice.finAll();
    }

    @PostMapping(path = "/creditsdetails/{id}")
    public ResponseEntity<?>  getcreditsdetail(@Valid @PathVariable long id, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(this.utilService.errorResult(result), HttpStatus.BAD_REQUEST);
        }
        return this.creditsdetailservice.findById(id);
    }

}
