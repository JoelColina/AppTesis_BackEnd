package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.AddressesDTO;
import com.mindhub.retailhome.dtos.CreditsHeaderDTO;
import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import com.mindhub.retailhome.mappers.CreditsHeaderMapper;
import com.mindhub.retailhome.mappers.PurchasingHeaderMapper;
import com.mindhub.retailhome.models.Account;
import com.mindhub.retailhome.models.Addresses;
import com.mindhub.retailhome.models.CreditsHeader;
import com.mindhub.retailhome.models.PurchasingHeader;
import com.mindhub.retailhome.repositories.CreditsHeaderRepository;
import com.mindhub.retailhome.repositories.PurchasingHeaderRepository;
import com.mindhub.retailhome.service.CreditsHeaderService;
import com.mindhub.retailhome.utils.Constants;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CreditsHeaderServiceImpl implements CreditsHeaderService {
    private Map<String, Object> response;
    private HttpStatus http;
    private CreditsHeaderDTO creditsHeaderDtoNew;
    private CreditsHeader creditsHeaderNew;
    private CreditsHeaderMapper creditsHeaderMapper;
    private final CreditsHeaderMapper mapper = Mappers.getMapper(CreditsHeaderMapper.class);
    private final CreditsHeaderRepository creditsHeaderRepository;

    CreditsHeaderServiceImpl(CreditsHeaderRepository creditsHeaderRepository) {
        this.creditsHeaderRepository = creditsHeaderRepository;
    }

    @Override
    public ResponseEntity<?> finAll() {
        response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        List<CreditsHeaderDTO> listDto = new ArrayList<>();

        try {
            this.creditsHeaderRepository.findAll().forEach(creditsHeader ->
                    listDto.add(this.creditsHeaderMapper.toCreditsHeaderDto(creditsHeader))
            );

            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.CREDIT_HEADER.CREDIT_HEADERS, listDto);
            this.http = HttpStatus.ACCEPTED;
        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(listDto, this.http);}

    @Override
    public ResponseEntity<?> findById(Long id) {
        response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        if (id == null) {
            return this.creditsHeaderRepository.findById(id).map(CreditsHeaderDTO::new).orElse(null);
        }
        return new ResponseEntity<Map<String, Object>>(response, this.http);}


    @Override
    public ResponseEntity<?> save(CreditsHeaderDTO creditsHeaderDTO) {
        this.response = new HashMap<>();
        this.creditsHeaderNew = null;
        this.creditsHeaderDtoNew = null;

        try {

            this.creditsHeaderNew = this.creditsHeaderRepository.save(this.creditsHeaderMapper.toCreditsHeader(creditsHeaderDTO));
            this.creditsHeaderNew.setEnabled(true);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, creditsHeaderDtoNew);
            this.http = HttpStatus.CREATED;
        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public boolean delete(CreditsHeaderDTO creditsHeaderDTO) {
        boolean operation = false;

        CreditsHeaderDTO creditsHeaderDtoNew = findById(creditsHeaderDTO.getIdClient());

        try {
            creditsHeaderDtoNew.setEnabled(false);
            update(creditsHeaderDtoNew);
            operation = true;

        }catch (Exception e){
            operation = false;
        }
        return operation;
    }

    @Override
    public ResponseEntity<?> update(CreditsHeaderDTO creditsHeaderDTO) {
        this.response = new HashMap<>();
        this.creditsHeaderDtoNew = null;
        this.creditsHeaderNew = null;

        try {
            creditsHeaderDTO = findById(creditsHeaderDTO.getIdClient());

            if(creditsHeaderDTO == null){
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else {
                creditsHeaderDtoNew.setRequestedAmount(creditsHeaderDTO.getRequestedAmount());
                creditsHeaderDtoNew.setQuotaNumber(creditsHeaderDTO.getQuotaNumber());

                creditsHeaderNew = this.creditsHeaderRepository.save(this.creditsHeaderMapper.toCreditsHeader(creditsHeaderDtoNew));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, creditsHeaderNew);
                http = HttpStatus.ACCEPTED;
            }
        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);

    }
}
