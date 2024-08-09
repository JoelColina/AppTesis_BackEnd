package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDTO findByEmail(String email) {
       ClientDTO clientDTO = null;
       return this.clientRepository.findByEmail(email).map(ClientDTO::new).orElse(null);
    }

    @Override
    public Set<ClientDTO> findAll() {
         return this.clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toSet());
    }

    @Override
    public ClientDTO finById(Long id)
    {
        return this.clientRepository.findById(id).map(ClientDTO::new).orElse(null);
    }

}
