package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.AddressesDTO;
import com.mindhub.homebanking.dtos.ShoppingDTO;
import com.mindhub.homebanking.repositories.AddressesRepository;
import com.mindhub.homebanking.repositories.ShoppingRepository;
import com.mindhub.homebanking.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Override
    public Set<ShoppingDTO> finAll() {
        return this.shoppingRepository.findAll().stream().map(ShoppingDTO::new).collect(Collectors.toSet());
    }

    @Override
    public ShoppingDTO finById(Long id) {
        return this.shoppingRepository.findById(id).map(ShoppingDTO::new).orElse(null);
    }
}
