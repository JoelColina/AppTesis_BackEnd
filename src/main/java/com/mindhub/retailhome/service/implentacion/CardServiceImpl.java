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
    public ResponseEntity<?> save(CardDTO cardDTO) {
        this.response = new HashMap<>();
        cardDtoNew = cardDTO;
        cardNew = null;

        try {
            this.cardNew = this.cardRepository.save(this.cardMapper.cardDtoToCard(cardDTO));

            this.cardNew.setEnabled(true);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, cardNew);
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

        CardDTO cardDtoNew = findById(cardDTO.getIdClient());

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
        this.cardNew = null;

        try {
            cardDTO = findById(cardDTO.getIdClient());
            if (cardDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;

            }else {

                cardDtoNew.setType(cardDTO.getType());
                cardDtoNew.setNumber(cardDTO.getNumber());
                cardDtoNew.setCvv(cardDTO.getCvv());
                cardDtoNew.setValidDate(cardDTO.getValidDate());
                cardDtoNew.setThruDate(cardDTO.getThruDate());
                cardDtoNew.setCardHolder(cardDTO.getCardHolder());
                cardDtoNew.setColor(cardDTO.getColor());
                cardDtoNew.setTotalLimit(cardDTO.getTotalLimit());
                cardDtoNew.setQuotaUsed(cardDTO.getQuotaUsed());
                cardDtoNew.setBalanceQuota(cardDTO.getBalanceQuota());

                cardNew = this.cardRepository.save(this.cardMapper.cardDtoToCard(cardDtoNew));
//                cardDtoNew = this.cardRepository.findById(cardDTO.getId()).map(CardDTO::new).orElse(null);

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, cardNew);
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
