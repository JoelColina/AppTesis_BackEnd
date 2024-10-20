package com.retailhome.controllers;

import com.retailhome.dtos.ClientDTO;

import com.retailhome.repositories.ClientRepository;
import com.retailhome.service.AddressesService;
import com.retailhome.service.ClientService;
import com.retailhome.service.UtilService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;
    private final AddressesService addressesService;
    private final UtilService utilService;

    @Autowired
    private ClientRepository clientRepository;

    public ClientController(ClientService clientService,
                            AddressesService addressesService,
                            UtilService utilService) {
        this.clientService = clientService;
        this.addressesService = addressesService;
        this.utilService = utilService;
    }

    @PostMapping()
    public ResponseEntity<?> post(@Valid @RequestBody ClientDTO clientDTO, BindingResult result) {
        if (result .hasErrors()){
            return new ResponseEntity<>( this.utilService.errorResult(result),HttpStatus.BAD_REQUEST );
        }
        return this.clientService.save(clientDTO);
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

//        clientRepository.save(new Client(nombres, apellidoPaterno, apellidoMaterno, rut, fechaNacimiento, numeroTelefono, email, cupoTotal, deudaCta, cupoDisponible, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @RequestMapping("/clients/current")
//    public ClientDTO getAll(Authentication authentication) {
//      //  Client client= clientRepository.findByEmail(authentication.getName());
//        ClientDTO client = clientRepository.findByEmail(authentication.getName()).map(ClientDTO::new).orElse(null);
//        return new ClientDTO(client);
//    }
}
