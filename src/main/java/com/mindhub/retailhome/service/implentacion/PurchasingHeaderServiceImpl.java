package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.AddressesDTO;
import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import com.mindhub.retailhome.mappers.PurchasingHeaderMapper;
import com.mindhub.retailhome.models.Addresses;
import com.mindhub.retailhome.models.PurchasingHeader;
import com.mindhub.retailhome.models.Transaction;
import com.mindhub.retailhome.repositories.PurchasingHeaderRepository;
import com.mindhub.retailhome.service.PurchasingHeaderService;
import com.mindhub.retailhome.utils.Constants;
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
    private PurchasingHeaderRepository purchasingHeaderRepository;

    PurchasingHeaderServiceImpl(PurchasingHeaderMapper purchasingHeaderMapper,
                                PurchasingHeaderRepository purchasingHeaderRepository,
                                PurchasingHeaderDTO purchasingHeaderDTO,
                                PurchasingHeader purchasingHeaderNew,
                                PurchasingHeaderDTO purchasingHeaderDtoNew
    ) {}


    @Override
    public Set<PurchasingHeaderDTO> finAll() {
        return Collections.singleton(this.purchasingHeaderMapper.purchasingHeaderToPurchasingHeaderDto(Optional.of((PurchasingHeader) this.purchasingHeaderRepository.findAll())));
        // return this.purchasingHeaderRepository.findAll().stream().map(PurchasingHeaderDTO::new).collect(Collectors.toSet());
    }

    @Override
    public PurchasingHeaderDTO findById(Long id) {
        return this.purchasingHeaderMapper.purchasingHeaderToPurchasingHeaderDto( this.purchasingHeaderRepository.findById(id));
        //return this.purchasingHeaderRepository.findById(id).map(PurchasingHeaderDTO::new).orElse(null);
    }

    @Override
    public List<PurchasingHeaderDTO> findPurchasingHeaderByClient(String idClient) {
        return List.of();
    }

//    @Override
//    public List<PurchasingHeaderDTO> findPurchasingHeaderByClient(String idClient) {
//        return this.purchasingHeaderRepository.findPurchasingHeaderByClient(idClient).stream().map(PurchasingHeaderDTO::new).collect(Collectors.toList());
//    }

    @Override
    public ResponseEntity<?> save(PurchasingHeaderDTO purchasingHeaderDTO) {
        this.response = new HashMap<>();
        this.purchasingHeaderNew = null;
        purchasingHeaderDtoNew = null;

        try {
            this.purchasingHeaderNew.setEnabled(true);

            this.purchasingHeaderNew = this.purchasingHeaderRepository.save(this.purchasingHeaderMapper.purchasingHeaderDtoToPurchasingHeader(purchasingHeaderDTO));
            this.purchasingHeaderDtoNew = purchasingHeaderMapper.purchasingHeaderToPurchasingHeaderDto(Optional.of(purchasingHeaderRepository.save(purchasingHeaderNew)));

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
       // List<PurchasingHeaderDTO> listDtoNew = findPurchasingHeaderByClient(purchasingHeaderDTO.getIdClient());

        try {
        //    if (listDtoNew.isEmpty()){
                operation = false;
         //   }else{
//                listDtoNew = listDtoNew.stream().filter(x -> x.equals(purchasingHeaderDTO)).toList();
//                PurchasingHeaderDTO deleteDto = getPurchasingHeaderDTODTO(purchasingHeaderDTO, (PurchasingHeader) listDtoNew);
//
//                deleteDto.setEnabled(false);
//                update(deleteDto);
//                operation = true;
//            }

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
            List<PurchasingHeaderDTO> listDtoNew = findPurchasingHeaderByClient(purchasingHeaderDTO.getIdClient());

            if (listDtoNew.isEmpty()){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else {

                listDtoNew = listDtoNew.stream().filter(x -> x.equals(purchasingHeaderDTO)).toList();
                PurchasingHeaderDTO purchasingHeaderDtoOld = getPurchasingHeaderDTODTO(purchasingHeaderDTO, (PurchasingHeader) listDtoNew);

                purchasingHeaderNew = this.purchasingHeaderRepository.save(this.purchasingHeaderMapper.purchasingHeaderDtoToPurchasingHeader(purchasingHeaderDtoOld));
                this.purchasingHeaderDtoNew = purchasingHeaderMapper.purchasingHeaderToPurchasingHeaderDto(Optional.of(purchasingHeaderRepository.save(purchasingHeaderNew)));

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

    private PurchasingHeaderDTO getPurchasingHeaderDTODTO(PurchasingHeaderDTO purchasingHeaderDTO, PurchasingHeader listDtoNew) {
        PurchasingHeaderDTO purchasingHeaderDtoOld = new PurchasingHeaderDTO(listDtoNew);

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

        return purchasingHeaderDtoOld;
    }
}
