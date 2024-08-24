package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import com.mindhub.retailhome.mappers.PurchasingHeaderMapper;
import com.mindhub.retailhome.models.AddressType;
import com.mindhub.retailhome.models.PurchasingHeader;
import com.mindhub.retailhome.repositories.PurchasingHeaderRepository;
import com.mindhub.retailhome.service.PurchasingHeaderService;
import com.mindhub.retailhome.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PurchasingHeaderServiceImpl implements PurchasingHeaderService {

    private Map<String, Object> response;
    private HttpStatus http;
    private PurchasingHeaderDTO purchasingHeaderDTONew;
    private PurchasingHeader purchasingHeaderNew;
    private PurchasingHeaderMapper purchasingHeaderMapper;

    @Autowired
    private PurchasingHeaderRepository purchasingHeaderRepository;

    @Override
    public Set<PurchasingHeaderDTO> finAll() {
        return this.purchasingHeaderRepository.findAll().stream().map(PurchasingHeaderDTO::new).collect(Collectors.toSet());
    }

    @Override
    public PurchasingHeaderDTO findById(Long id) {
        return this.purchasingHeaderRepository.findById(id).map(PurchasingHeaderDTO::new).orElse(null);
    }

    @Override
    public ResponseEntity<?> save(PurchasingHeaderDTO purchasingHeaderDTO) {
        this.response = new HashMap<>();
        purchasingHeaderNew = null;
        try {

            this.purchasingHeaderNew = this.purchasingHeaderRepository.save(this.purchasingHeaderMapper.purchasingHeaderDtoToPurchasingHeader(purchasingHeaderDTO));

//            this.accountRepository.save(accountDTO);
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

        PurchasingHeaderDTO purchasingHeaderDTONew = findById(purchasingHeaderDTO.getIdClient());

        try {
            purchasingHeaderDTONew.setEnabled(false);
            update(purchasingHeaderDTONew);
            operation = true;

        }catch (Exception e){
            operation = false;
        }

        return operation;
    }

    @Override
    public ResponseEntity<?> update(PurchasingHeaderDTO purchasingHeaderDTO) {
        this.response = new HashMap<>();
        this.purchasingHeaderDTONew = null;
        this.purchasingHeaderNew = null;

        try {
            purchasingHeaderDTO = findById(purchasingHeaderDTO.getIdClient());
            if (purchasingHeaderDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else {

                purchasingHeaderDTONew.setTrade(purchasingHeaderDTO.getTrade());
                purchasingHeaderDTONew.setProduct(purchasingHeaderDTO.getProduct());
                purchasingHeaderDTONew.setNumberBuy(purchasingHeaderDTO.getNumberBuy());
                purchasingHeaderDTONew.setSku(purchasingHeaderDTO.getSku());
                purchasingHeaderDTONew.setPurchaseDate(purchasingHeaderDTO.getPurchaseDate());
                purchasingHeaderDTONew.setAmount(purchasingHeaderDTO.getAmount());
                purchasingHeaderDTONew.setWorth(purchasingHeaderDTO.getWorth());
                purchasingHeaderDTONew.setNroQuotes(purchasingHeaderDTO.getNroQuotes());
                purchasingHeaderDTONew.setTotalValue(purchasingHeaderDTO.getTotalValue());
                purchasingHeaderDTONew.setCardType(purchasingHeaderDTO.getCardType());
                purchasingHeaderDTONew.setType(purchasingHeaderDTO.getType());
                purchasingHeaderDTONew.setDeliverDate(purchasingHeaderDTO.getDeliverDate());
                purchasingHeaderDTONew.setDeliveryAddress(purchasingHeaderDTO.getDeliveryAddress());
                purchasingHeaderDTONew.setRetiredBy(purchasingHeaderDTO.getRetiredBy());

                purchasingHeaderNew = this.purchasingHeaderRepository.save(this.purchasingHeaderMapper.purchasingHeaderDtoToPurchasingHeader(purchasingHeaderDTONew));

//                purchasingHeaderDTONew = this.purchasingHeaderRepository.findById(purchasingHeaderDTO.getId()).map(PurchasingHeaderDTO::new).orElse(null);

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, purchasingHeaderNew);
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
