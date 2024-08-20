package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.AddressesDTO;
import com.mindhub.retailhome.models.Addresses;
import com.mindhub.retailhome.repositories.AddressesRepository;
import com.mindhub.retailhome.service.AddressesService;
import com.mindhub.retailhome.utils.Constants;
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
    private AddressesDTO addressesDtoNew;
    private AddressesDTO addressesDtoOld;
    private Addresses addressesNew;

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
        addressesDtoNew = addressesDTO;

        try {
//            this.accountRepository.save(accountDTO);
            this.addressesDtoNew.setEnabled(true);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, addressesDtoNew);
            this.http = HttpStatus.CREATED;

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public boolean delete(AddressesDTO addressesDTO) {
        boolean operation = false;
        AddressesDTO addressesDtoNew = findById(addressesDTO.getId());
        try {

            addressesDtoNew.setEnabled(false);
            update(addressesDtoNew);
            operation = true;
        }catch (Exception e){

            operation = false;
        }

        return operation;
    }

    @Override
    public ResponseEntity<?> update(AddressesDTO addressesDTO) {
//        addressesDtoOld = new AddressesDTO();
        addressesNew = new Addresses();
        this.response = new HashMap<>();
        this.addressesDtoNew = null;

        try {
            addressesDTO = findById(addressesDTO.getId());

            if (addressesDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else {
                addressesDtoNew = this.addressesRepository.findById(addressesDTO.getId()).map(AddressesDTO::new).orElse(null);

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, addressesDtoNew);
                http = HttpStatus.ACCEPTED;
            }

        }catch (Exception e){
//            response new ResponseEntity<>(accountDTONew, HttpStatus.BAD_REQUEST);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }

        //que se puede enviar en el response entity
        return new ResponseEntity<>(this.response,this.http);
    }
}
