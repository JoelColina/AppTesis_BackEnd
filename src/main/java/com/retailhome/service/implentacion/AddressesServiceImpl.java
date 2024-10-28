package com.retailhome.service.implentacion;

import com.retailhome.dtos.AddressesDTO;
import com.retailhome.mappers.AddressesMapper;
import com.retailhome.models.Addresses;
import com.retailhome.repositories.AddressesRepository;
import com.retailhome.service.AddressesService;
import com.retailhome.utils.Constants;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressesServiceImpl implements AddressesService {

    private Map<String, Object> response;
    private HttpStatus http;
    private AddressesDTO addressesDtoNew;
    private Addresses addressesNew;
    private final AddressesMapper mapper = Mappers.getMapper(AddressesMapper.class);
    private  final AddressesRepository addressesRepository;

    public AddressesServiceImpl(AddressesRepository addressesRepository  ) {
        this.addressesRepository=addressesRepository;
    }

    @Override
    public ResponseEntity<?> finAll() {

        this.response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        List<AddressesDTO> listDto = new ArrayList<>();

        try {
            listDto = this.mapper.toDTOList( this.addressesRepository.findAll());
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, listDto);
            this.http = HttpStatus.ACCEPTED;

        } catch (Exception e) {

             this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);

            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, this.http);
    }

    @Override
    public ResponseEntity    findById(Long id) {
        this.response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        try {
            if (id == null) {
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.BAD_REQUEST;
            }else {
                AddressesDTO addressesDTO = this.mapper.toAddressesDto(this.addressesRepository.findById(id).orElse(null));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.mapper.toAddressesDto(this.addressesRepository.findById(id).orElse(null));
                this.response.put(Constants.ADDRESS.ADDRES, addressesDTO);
                http = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public ResponseEntity<?> save(AddressesDTO addressesDTO) {
        this.response = new HashMap<>();
        addressesDtoNew = addressesDTO;
        addressesNew = null;

        try {
            this.addressesNew = this.addressesRepository.save(this.mapper.toAddresses(addressesDTO));
            this.addressesNew.setEnabled(true);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, addressesNew);
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



        AddressesDTO addressesDtoNew = findById(addressesDTO.getIdClient());

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
        this.addressesNew = new Addresses();
        this.response = new HashMap<>();
        this.addressesDtoNew = null;

        try {
            addressesDTO = findById(addressesDTO.getIdClient());

            if (addressesDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else {

                addressesDtoNew.setAddress(addressesDTO.getAddress());
                addressesDtoNew.setNumber(addressesDTO.getNumber());
                addressesDtoNew.setCity(addressesDTO.getCity());
                addressesDtoNew.setCommune(addressesDTO.getCommune());
                addressesDtoNew.setPostalCode(addressesDTO.getPostalCode());
                addressesDtoNew.setType(addressesDTO.getType());

                addressesNew = this.addressesRepository.save(this.addressesMapper.addressesDtoToAddresses(addressesDtoNew));
//                addressesDtoNew = this.addressesRepository.findById(addressesDTO.getId()).map(AddressesDTO::new).orElse(null);
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, addressesDtoNew);
                http = HttpStatus.ACCEPTED;
            }

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response,this.http);
    }
}
