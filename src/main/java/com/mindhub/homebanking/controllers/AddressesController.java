package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AddressesDTO;
import com.mindhub.homebanking.dtos.CreditsHeaderDTO;
import com.mindhub.homebanking.repositories.AddressesRepository;
import com.mindhub.homebanking.service.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class AddressesController {

    private final AddressesService addressesService;

    @Autowired
    private AddressesRepository addressesRepository;

    public AddressesController(AddressesService addressesService){
        this.addressesService = addressesService;
    }

    @RequestMapping("/addresses")
    public Set<AddressesDTO> getcard(){
        return this.addressesService.finAll();
    }
    @RequestMapping("/shopping/{id}")
    public AddressesDTO getcard(@PathVariable long id){
        return this.addressesService.finById(id);
    }

}
