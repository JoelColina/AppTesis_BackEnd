package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.CardDTO;
import com.mindhub.retailhome.mappers.CardMapper;
import com.mindhub.retailhome.models.Card;
import com.mindhub.retailhome.repositories.CardRepository;
import com.mindhub.retailhome.service.CardService;
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
public class CardServiceImpl implements CardService {

    private Map<String, Object> response;
    private HttpStatus http;
    private CardDTO cardDtoNew;
    private CardDTO cardDtoOld;
    private Card cardNew;
    private CardMapper cardMapper;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Set<CardDTO> finAll() {
        return this.cardRepository.findAll().stream().map(CardDTO::new).collect(Collectors.toSet());
    }

    @Override
    public CardDTO findById(Long id) {
        return this.cardRepository.findById(id).map(CardDTO::new).orElse(null);
    }

    @Override
    public CardDTO findByIdClient(String idClient) {
        return (CardDTO) this.cardRepository.findCardDto(idClient).mapToObj(CardDTO::new).toList();
    }

    @Override
    public ResponseEntity<?> save(CardDTO cardDTO) {
        this.response = new HashMap<>();
        cardDtoNew = null;
        cardNew = null;

        try {

            this.cardNew = this.cardRepository.save(this.cardMapper.cardDtoToCard(cardDTO));
            this.cardDtoNew = cardMapper.cardToCardDto(cardRepository.save(cardNew));

            this.cardNew.setEnabled(true);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, cardDtoNew);
            this.http = HttpStatus.CREATED;

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public boolean delete(CardDTO cardDTO) {
        boolean operation = false;

        CardDTO cardDtoNew = findByIdClient(cardDTO.getIdClient());

        try {
            cardDtoNew.setEnabled(false);
            update(cardDtoNew);
            operation = true;

        }catch (Exception e){
            operation = false;
        }

        return operation;
    }


    @Override
    public ResponseEntity<?> update(CardDTO cardDTO) {
        this.response = new HashMap<>();
        this.cardDtoNew = null;
        this.cardDtoOld = null;
        this.cardNew = null;

        try {
            cardDTO = findByIdClient(cardDTO.getIdClient());
            if (cardDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;

            }else {

                cardDtoOld.setType(cardDTO.getType());
                cardDtoOld.setNumber(cardDTO.getNumber());
                cardDtoOld.setCvv(cardDTO.getCvv());
                cardDtoOld.setValidDate(cardDTO.getValidDate());
                cardDtoOld.setThruDate(cardDTO.getThruDate());
                cardDtoOld.setCardHolder(cardDTO.getCardHolder());
                cardDtoOld.setColor(cardDTO.getColor());
                cardDtoOld.setTotalLimit(cardDTO.getTotalLimit());
                cardDtoOld.setQuotaUsed(cardDTO.getQuotaUsed());
                cardDtoOld.setBalanceQuota(cardDTO.getBalanceQuota());

                cardNew = this.cardRepository.save(this.cardMapper.cardDtoToCard(cardDtoOld));
                this.cardDtoNew = cardMapper.cardToCardDto(cardRepository.save(cardNew));

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, cardDtoNew);
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
