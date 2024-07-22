package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.CityDTO;
import com.mindhub.homebanking.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping("/city")
    public List<CityDTO> getLoans() {
        return this.cityService.findAll();
    }

    @RequestMapping("/city/{id}")
    public CityDTO getLoans(@PathVariable Long id){
        return this.cityService.findById(id);
    }

}
