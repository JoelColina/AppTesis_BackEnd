package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.AddressesDTO;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.AddressesRepository;
import com.mindhub.homebanking.service.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AddressesServiceImpl implements AddressesService {

    private Map<String, Object> response;
    private HttpStatus http;
    private AddressesDTO addressesDTONew;

    @Autowired
    private AddressesRepository addressesRepository;

    @Override
    public Set<AddressesDTO> finAll() {
        return this.addressesRepository.findAll().stream().map(AddressesDTO::new).collect(Collectors.toSet());
    }

    @Override
    public AddressesDTO findById(Long id) {
        return this.addressesRepository.findById(id).map(AddressesDTO::new).orElse(null);
    }

    @Override
    public ResponseEntity<?> save(AddressesDTO addressesDTO) {
        this.response = new HashMap<>();
        addressesDTONew = addressesDTO;

        try {
//            this.accountRepository.save(accountDTO);
            this.addressesDTONew.setEnabled(true);
            this.response.put("mensaje genenetal", "OPERATION_OK");
            this.response.put("Direccion creada", addressesDTONew);
            this.http = HttpStatus.CREATED;

        }catch (Exception e){
            this.response.put("mensaje genenetal", "OPERATION_NOT_OK");
            this.response.put("mensaje ERROR", e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public boolean delete(AddressesDTO addressesDTO) {
        boolean operation = false;

        AddressesDTO addressesDTONew = findById(addressesDTO.getId());

        try {
            addressesDTONew.setEnabled(false);

            update(addressesDTONew);

            operation = true;

        }catch (Exception e){
            operation = false;
        }

        return operation;
    }

    @Override
    public ResponseEntity<?> update(AddressesDTO addressesDTO) {

        this.response = new HashMap<>();
        this.addressesDTONew = null;

        try {
            addressesDTONew = this.addressesRepository.findById(addressesDTO.getId()).map(AddressesDTO::new).orElse(null);

            this.response.put("Mensaje General","Operacion OK");
            this.response.put("Datos actualizados",addressesDTONew);
            http = HttpStatus.ACCEPTED;

        }catch (Exception e){
//            response new ResponseEntity<>(accountDTONew, HttpStatus.BAD_REQUEST);
            this.response.put("Mensaje General","Operacion NOOK");
            this.response.put("Mensaje error",e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }

        //que se puede enviar en el response entity
        return new ResponseEntity<>(this.response,this.http);
    }
}
