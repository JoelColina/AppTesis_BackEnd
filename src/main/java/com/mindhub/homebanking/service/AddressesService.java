package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.AddressesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface AddressesService {

    Set<AddressesDTO> finAll();

    AddressesDTO finById(Long id);

    // agregar
    AddressesDTO save (AddressesDTO addressesDTO);

    // eliminar
    boolean delete (AddressesDTO addressesDTO);

    // actualizar
    ResponseEntity<?> update(AddressesDTO addressesDTO);
}
