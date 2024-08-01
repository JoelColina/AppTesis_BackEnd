package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.AddressesDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface AddressesService {

    Set<AddressesDTO> finAll();

    AddressesDTO finById(Long id);
}
