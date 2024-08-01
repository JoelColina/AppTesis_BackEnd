package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.AddressesDTO;
import com.mindhub.homebanking.service.AddressesService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AddressesServiceImpl implements AddressesService {

    @Override
    public Set<AddressesDTO> finAll() {
        return Set.of();
    }

    @Override
    public AddressesDTO finById(Long id) {
        return null;
    }
}
