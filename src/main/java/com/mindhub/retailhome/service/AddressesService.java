package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.AddressesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface AddressesService {

    Set<AddressesDTO> finAll();
    AddressesDTO findById(Long id);
    List<AddressesDTO> findAddressesByClient(String idClient);
    ResponseEntity<?> save (AddressesDTO addressesDTO);
    boolean delete (AddressesDTO addressesDTO);
    ResponseEntity<?> update(AddressesDTO addressesDTO);
}
