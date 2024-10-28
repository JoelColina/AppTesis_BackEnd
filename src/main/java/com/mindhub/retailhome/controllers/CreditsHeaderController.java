package com.mindhub.retailhome.controllers;

import com.mindhub.retailhome.dtos.CreditsHeaderDTO;
import com.mindhub.retailhome.repositories.CreditsHeaderRepository;
import com.mindhub.retailhome.service.CreditsHeaderService;
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
public class CreditsHeaderController {

    private final CreditsHeaderService creditsheaderservice;
    private final UtilService utilService;

    public CreditsHeaderController(CreditsHeaderService creditsheaderservice, UtilService utilService) {
        this.creditsheaderservice = creditsheaderservice;
        this.utilService = utilService;
    }

    @GetMapping(path = "/creditsheaders")
    public ResponseEntity<?> getcreditsheaders(){
        return this.creditsheaderservice.finAll();
    }

    @PostMapping(path = "/creditsheaders/{id}")
    public ResponseEntity<?> getcreditsheader(@Valid @PathVariable Long id, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(this.utilService.errorResult(result), HttpStatus.BAD_REQUEST);
        }
        return this.creditsheaderservice.findById(id);
    }

}
