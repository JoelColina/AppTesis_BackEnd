package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.AccountDTO;
import com.mindhub.retailhome.dtos.AddressesDTO;
import com.mindhub.retailhome.dtos.CardDTO;
import com.mindhub.retailhome.mappers.AccountMapper;
import com.mindhub.retailhome.mappers.CardMapper;
import com.mindhub.retailhome.models.Addresses;
import com.mindhub.retailhome.models.Card;
import com.mindhub.retailhome.models.CreditDetail;
import com.mindhub.retailhome.repositories.AccountRepository;
import com.mindhub.retailhome.repositories.CardRepository;
import com.mindhub.retailhome.service.CardService;
import com.mindhub.retailhome.utils.Constants;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    private Map<String, Object> response;
    private HttpStatus http;
    private CardDTO cardDtoNew;
    private CardDTO cardDtoOld;
    private Card cardNew;
    private CardMapper cardMapper;
    private final CardMapper mapper = Mappers.getMapper(CardMapper.class);

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    @Autowired
    private CardRepository cardRepository;

    @Override
    public ResponseEntity<?>  finAll() {
        response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        List<CardDTO> listDto = new ArrayList<>();

        try {
            this.cardRepository.findAll().forEach(card ->
                    listDto.add(this.cardMapper.toCardDTO(card)));

            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.ACCOUNT.ACCOUNTS, listDto);
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
        this.cardDtoNew = new CardDTO();
        this.http = HttpStatus.NOT_FOUND;
        try {
            if (id != null) {
                this.cardDtoNew = mapper.toCardDTO(cardRepository.findById(id).orElse(null));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.ACCOUNT.ACCOUNT,  this.cardDtoNew);
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
    public ResponseEntity<?> save(CardDTO cardDTO) {
        this.response = new HashMap<>();
        cardDtoNew = null;
        cardNew = null;

        try {

            this.cardNew = this.cardRepository.save(this.cardMapper.toCard(cardDTO));
            this.cardDtoNew = cardMapper.toCardDTO(cardRepository.save(cardNew));

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
    public ResponseEntity<?>  delete(CardDTO cardDTO) {
        this.response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        this.cardDtoNew = new CardDTO();
        this.cardNew = new Card();
        try {
            this.cardNew = (Card) this.cardRepository.findCardByClient(String.valueOf(cardDTO.getIdClient()));

            if (this.cardNew == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            } else {

                this.cardNew.setEnabled(false);
                this.cardRepository.save(this.cardNew);
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.http = HttpStatus.ACCEPTED;
            }
            this.cardNew.setEnabled(false);
            update(cardDtoNew);

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response,this.http);
    }


    @Override
    public ResponseEntity<?> update(CardDTO cardDTO) {
        this.response = new HashMap<>();
        this.cardDtoNew = null;
        this.cardNew = null;

        try {
            this.cardNew = null;//this.cardRepository.findById(CardDTO.getIdClient().orElse(null));
            this.cardDtoNew = mapper.toCardDTO(this.cardNew);

            if (cardDtoNew == null){
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

                cardNew = this.cardRepository.save(this.cardMapper.toCard(cardDtoNew));

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
