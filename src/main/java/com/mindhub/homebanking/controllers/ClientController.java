package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/clients")
    public Set<ClientDTO> getClients(){
        return this.clientService.findAll();
   }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClients(@PathVariable Long id){
       return this.clientService.finById(id);
   }

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String nombres, @RequestParam String apellidoPaterno,
            @RequestParam String apellidoMaterno, @RequestParam String rut,
            @RequestParam Date fechaNacimiento, @RequestParam Number numeroTelefono,
            @RequestParam Number cupoTotal,@RequestParam Number deudaCta,
            @RequestParam Number cupoDisponible,
            @RequestParam String email, @RequestParam String password) {

//        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
//            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
//        }

        if (clientRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        clientRepository.save(new Client(nombres, apellidoPaterno, apellidoMaterno, rut, fechaNacimiento, numeroTelefono, email, cupoTotal, deudaCta, cupoDisponible, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/clients/current")
    public ClientDTO getAll(Authentication authentication) {
        Client client= clientRepository.findByEmail(authentication.getName());
        return new ClientDTO(client);
    }
}
