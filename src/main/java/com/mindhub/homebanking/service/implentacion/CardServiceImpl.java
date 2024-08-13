package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.AddressType;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.service.CardService;
import com.mindhub.homebanking.utils.Constants;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    private Map<String, Object> response;
    private HttpStatus http;
    private CardDTO cardDtoNew;

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
        try {
//            this.accountRepository.save(accountDTO);
            this.cardDtoNew.setEnabled(true);
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

        CardDTO cardDtoNew = findById(cardDTO.getId());

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

        try {
            cardDTO = findById(cardDTO.getId());
            if (cardDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;

            }else {
                cardDtoNew = this.cardRepository.findById(cardDTO.getId()).map(CardDTO::new).orElse(null);

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, cardDtoNew);
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
