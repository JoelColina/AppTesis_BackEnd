package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AddressesDTO;
import com.mindhub.homebanking.service.AddressesService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class AddressesController {

    private final AddressesService addressesService;

    public AddressesController(AddressesService addressesService){
        this.addressesService = addressesService;
    }

    @RequestMapping("/addresses")
    public Set<AddressesDTO> getAddresses(){
        return this.addressesService.finAll();
    }
    @RequestMapping("/addresses/{id}")
    public AddressesDTO getAddresses(@PathVariable long id){
        return this.addressesService.findById(id);
    }

}
