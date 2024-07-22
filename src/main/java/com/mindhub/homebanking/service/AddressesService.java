package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.AddressesDTO;

import java.util.Set;

public interface AddressesService {

    Set<AddressesDTO> finAll();

    AddressesDTO finById(Long id);
}
