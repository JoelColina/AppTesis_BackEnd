package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.AccountDTO;
import com.mindhub.retailhome.dtos.AddressesDTO;
import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import com.mindhub.retailhome.dtos.TransactionDTO;
import com.mindhub.retailhome.mappers.AccountMapper;
import com.mindhub.retailhome.mappers.PurchasingHeaderMapper;
import com.mindhub.retailhome.models.Addresses;
import com.mindhub.retailhome.models.PurchasingHeader;
import com.mindhub.retailhome.models.Transaction;
import com.mindhub.retailhome.repositories.AccountRepository;
import com.mindhub.retailhome.repositories.PurchasingHeaderRepository;
import com.mindhub.retailhome.service.PurchasingHeaderService;
import com.mindhub.retailhome.utils.Constants;
import com.mindhub.retailhome.utils.enums.AddressType;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchasingHeaderServiceImpl implements PurchasingHeaderService {

    private Map<String, Object> response;
    private HttpStatus http;
    private PurchasingHeaderDTO purchasingHeaderDtoNew;
    private PurchasingHeader purchasingHeaderNew;
    private PurchasingHeaderMapper purchasingHeaderMapper;
    private final PurchasingHeaderMapper mapper = Mappers.getMapper(PurchasingHeaderMapper.class);

    PurchasingHeaderServiceImpl( PurchasingHeaderRepository purchasingHeaderRepository,
                                PurchasingHeader purchasingHeaderNew,
                                PurchasingHeaderDTO purchasingHeaderDtoNew
    ) {
        this.purchasingHeaderRepository = purchasingHeaderRepository;
        this.purchasingHeaderDtoNew = purchasingHeaderDtoNew;
        this.purchasingHeaderNew = purchasingHeaderNew;
    }
    private final PurchasingHeaderRepository purchasingHeaderRepository;


    @Override
    public ResponseEntity<?> findAll() {
        response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        List<PurchasingHeaderDTO> listDto = new ArrayList<>();

        try {
            this.purchasingHeaderRepository.findAll().forEach(purchasingHeader ->
                    listDto.add(this.purchasingHeaderMapper.toPurchasingHeaderDto(purchasingHeader))
            );

            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.PURCHASING_HEADER.PURCHASING_HEADERS, listDto);
            this.http = HttpStatus.ACCEPTED;
        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(listDto, this.http);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        if (id == null) {
            return this.purchasingHeaderRepository.findById(id).map(PurchasingHeaderDTO::new).orElse(null);
        }
        return new ResponseEntity<Map<String, Object>>(response, this.http);
    }

    @Override
    public ResponseEntity<?> save(PurchasingHeaderDTO purchasingHeaderDTO) {
        this.response = new HashMap<>();
        this.purchasingHeaderNew = null;
        this.purchasingHeaderDtoNew = null;

        try {

            this.purchasingHeaderNew = this.purchasingHeaderRepository.save(this.purchasingHeaderMapper.ToPurchasingHeader(purchasingHeaderDTO));
            this.purchasingHeaderNew.setEnabled(true);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, purchasingHeaderNew);
            this.http = HttpStatus.CREATED;
        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public boolean delete(PurchasingHeaderDTO purchasingHeaderDTO) {
        boolean operation = false;
      //  PurchasingHeaderDTO purchasingHeaderDtoNew = findById(purchasingHeaderDTO.getIdClient());
        ResponseEntity<?> responseEntity = findById(purchasingHeaderDTO.getIdClient());
        PurchasingHeaderDTO purchasingHeaderDtoNew = (PurchasingHeaderDTO) responseEntity.getBody();

        try {
            purchasingHeaderDtoNew.setEnabled(false);
            update(purchasingHeaderDtoNew);
            operation = true;

        }catch (Exception e){
            operation = false;
        }
        return operation;
    }

    @Override
    public ResponseEntity<?> update(PurchasingHeaderDTO purchasingHeaderDTO) {
        this.response = new HashMap<>();
        this.purchasingHeaderDtoNew = null;
        this.purchasingHeaderNew = null;

        try {
            purchasingHeaderDTO = findById(purchasingHeaderDTO.getIdClient());

            if(purchasingHeaderDTO == null){
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else {
                purchasingHeaderDtoNew.setTrade(purchasingHeaderDTO.getTrade());
                purchasingHeaderDtoNew.setProduct(purchasingHeaderDTO.getProduct());
                purchasingHeaderDtoNew.setNumberBuy(purchasingHeaderDTO.getNumberBuy());
                purchasingHeaderDtoNew.setSku(purchasingHeaderDTO.getSku());
                purchasingHeaderDtoNew.setPurchaseDate(purchasingHeaderDTO.getPurchaseDate());
                purchasingHeaderDtoNew.setAmount(purchasingHeaderDTO.getAmount());
                purchasingHeaderDtoNew.setWorth(purchasingHeaderDTO.getWorth());
                purchasingHeaderDtoNew.setNroQuotes(purchasingHeaderDTO.getNroQuotes());
                purchasingHeaderDtoNew.setTotalValue(purchasingHeaderDTO.getTotalValue());
                purchasingHeaderDtoNew.setCardType(purchasingHeaderDTO.getCardType());
                purchasingHeaderDtoNew.setType(purchasingHeaderDTO.getType());
                purchasingHeaderDtoNew.setDeliverDate(purchasingHeaderDTO.getDeliverDate());
                purchasingHeaderDtoNew.setDeliveryAddress(purchasingHeaderDTO.getDeliveryAddress());
                purchasingHeaderDtoNew.setRetiredBy(purchasingHeaderDTO.getRetiredBy());

                purchasingHeaderNew = this.purchasingHeaderRepository.save(this.purchasingHeaderMapper.ToPurchasingHeader(purchasingHeaderDtoNew));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, purchasingHeaderNew);
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
