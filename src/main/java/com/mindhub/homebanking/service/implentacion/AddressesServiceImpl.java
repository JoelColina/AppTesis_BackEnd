package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.AddressesDTO;
import com.mindhub.homebanking.repositories.AddressesRepository;
import com.mindhub.homebanking.service.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class AddressesServiceImpl implements AddressesService {

    @Autowired
    private AddressesRepository addressesRepository;

    @Override
    public Set<AddressesDTO> finAll() {
        return this.addressesRepository.findAll().stream().map(AddressesDTO::new).collect(Collectors.toSet());
    }

    @Override
    public AddressesDTO finById(Long id) {
        return this.addressesRepository.findById(id).map(AddressesDTO::new).orElse(null);
    }
}
