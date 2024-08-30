package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import com.mindhub.retailhome.mappers.PurchasingHeaderMapper;
import com.mindhub.retailhome.models.PurchasingHeader;
import com.mindhub.retailhome.repositories.PurchasingHeaderRepository;
import com.mindhub.retailhome.service.PurchasingHeaderService;
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
public class PurchasingHeaderServiceImpl implements PurchasingHeaderService {

    private Map<String, Object> response;
    private HttpStatus http;
    private PurchasingHeaderDTO purchasingHeaderDtoNew;
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
    public PurchasingHeaderDTO PurchasingHeaderDto(String idClient) {
        return (PurchasingHeaderDTO) this.purchasingHeaderRepository.PurchasingHeaderDto(idClient).mapToObj(PurchasingHeaderDTO::new).toList();
    }

    @Override
    public ResponseEntity<?> save(PurchasingHeaderDTO purchasingHeaderDTO) {
        this.response = new HashMap<>();
        PurchasingHeader purchasingHeaderNew = null;
        purchasingHeaderDtoNew = null;

        try {
            this.purchasingHeaderNew.setEnabled(true);

            this.purchasingHeaderNew = this.purchasingHeaderRepository.save(this.purchasingHeaderMapper.purchasingHeaderDtoToPurchasingHeader(purchasingHeaderDTO));
            this.purchasingHeaderDtoNew = purchasingHeaderMapper.purchasingHeaderToPurchasingHeaderDto(purchasingHeaderRepository.save(purchasingHeaderNew));

            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, purchasingHeaderDtoNew);
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

        PurchasingHeaderDTO purchasingHeaderDTONew = PurchasingHeaderDto(purchasingHeaderDTO.getIdClient());

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
        PurchasingHeaderDTO purchasingHeaderDtoOld = null;
        this.purchasingHeaderDtoNew = null;
        this.purchasingHeaderNew = null;

        try {
            purchasingHeaderDTO = PurchasingHeaderDto(purchasingHeaderDTO.getIdClient());
            if (purchasingHeaderDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else {

                purchasingHeaderDtoOld.setTrade(purchasingHeaderDTO.getTrade());
                purchasingHeaderDtoOld.setProduct(purchasingHeaderDTO.getProduct());
                purchasingHeaderDtoOld.setNumberBuy(purchasingHeaderDTO.getNumberBuy());
                purchasingHeaderDtoOld.setSku(purchasingHeaderDTO.getSku());
                purchasingHeaderDtoOld.setPurchaseDate(purchasingHeaderDTO.getPurchaseDate());
                purchasingHeaderDtoOld.setAmount(purchasingHeaderDTO.getAmount());
                purchasingHeaderDtoOld.setWorth(purchasingHeaderDTO.getWorth());
                purchasingHeaderDtoOld.setNroQuotes(purchasingHeaderDTO.getNroQuotes());
                purchasingHeaderDtoOld.setTotalValue(purchasingHeaderDTO.getTotalValue());
                purchasingHeaderDtoOld.setCardType(purchasingHeaderDTO.getCardType());
                purchasingHeaderDtoOld.setType(purchasingHeaderDTO.getType());
                purchasingHeaderDtoOld.setDeliverDate(purchasingHeaderDTO.getDeliverDate());
                purchasingHeaderDtoOld.setDeliveryAddress(purchasingHeaderDTO.getDeliveryAddress());
                purchasingHeaderDtoOld.setRetiredBy(purchasingHeaderDTO.getRetiredBy());

                purchasingHeaderNew = this.purchasingHeaderRepository.save(this.purchasingHeaderMapper.purchasingHeaderDtoToPurchasingHeader(purchasingHeaderDtoOld));
                this.purchasingHeaderDtoNew = purchasingHeaderMapper.purchasingHeaderToPurchasingHeaderDto(purchasingHeaderRepository.save(purchasingHeaderNew));

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, purchasingHeaderDtoNew);
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
