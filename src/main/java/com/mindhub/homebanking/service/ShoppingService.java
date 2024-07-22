package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.ShoppingDTO;

import java.util.Set;

public interface ShoppingService {

    Set<ShoppingDTO> finAll();

    ShoppingDTO finById(Long id);

}
