package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ShoppingDTO;
import com.mindhub.homebanking.repositories.ShoppingRepository;
import com.mindhub.homebanking.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ShoppingController {

    private final ShoppingService shoppingService;

    @Autowired
    private ShoppingRepository shoppingRepository;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @RequestMapping("/shopping")
    public Set<ShoppingDTO> getcard(){
        return this.shoppingService.finAll();
    }

    @RequestMapping("/shopping/{id}")
    public ShoppingDTO getcard(@PathVariable long id){
        return this.shoppingService.finById(id);
    }
}
