package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.*;
import com.mindhub.retailhome.mappers.AccountMapper;
import com.mindhub.retailhome.mappers.PurchasingHeaderMapper;
import com.mindhub.retailhome.models.Addresses;
import com.mindhub.retailhome.models.CreditsHeader;
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
        this.purchasingHeaderDtoNew = new PurchasingHeaderDTO();
        this.http = HttpStatus.NOT_FOUND;
        try {
            if (id != null) {
                this.purchasingHeaderDtoNew = mapper.toPurchasingHeaderDto(purchasingHeaderRepository.findById(id).orElse(null));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.PURCHASING_HEADER.PURCHASING_HEADERS,  this.purchasingHeaderDtoNew);
                this.http = HttpStatus.ACCEPTED;
            } else {
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, this.http);
    }

    @Override
    public ResponseEntity<?> save(PurchasingHeaderDTO purchasingHeaderDTO) {
        this.response = new HashMap<>();
        this.purchasingHeaderNew = null;
        this.purchasingHeaderDtoNew = null;

        try {

            this.purchasingHeaderNew = this.purchasingHeaderRepository.save(this.purchasingHeaderMapper.toPurchasingHeader(purchasingHeaderDTO));
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
    public ResponseEntity<?>  delete(PurchasingHeaderDTO purchasingHeaderDTO) {
        this.response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        this.purchasingHeaderDtoNew = new PurchasingHeaderDTO();
        this.purchasingHeaderNew = new PurchasingHeader();

        try{
            this.purchasingHeaderNew = this.purchasingHeaderRepository.findById(purchasingHeaderDTO.getIdClient()).orElse(null);

            if(this.purchasingHeaderNew == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else{
                this.purchasingHeaderNew.setEnabled(false);
                this.purchasingHeaderRepository.save(this.purchasingHeaderNew);
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.http = HttpStatus.ACCEPTED;
            }
            this.purchasingHeaderNew.setEnabled(false);
            update(purchasingHeaderDtoNew);

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR,e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response,this.http);
    }

    @Override
    public ResponseEntity<?> update(PurchasingHeaderDTO purchasingHeaderDTO) {
        this.response = new HashMap<>();
        this.purchasingHeaderDtoNew = null;
        this.purchasingHeaderNew = null;
        this.http = HttpStatus.NOT_FOUND;

        try {

            this.purchasingHeaderNew = this.purchasingHeaderRepository.findById(purchasingHeaderDTO.getIdClient()).orElse(null);
            this.purchasingHeaderDtoNew = mapper.toPurchasingHeaderDto(this.purchasingHeaderNew);

            if(purchasingHeaderDtoNew == null){
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

                purchasingHeaderNew = this.purchasingHeaderRepository.save(this.purchasingHeaderMapper.toPurchasingHeader(purchasingHeaderDtoNew));
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
