package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.CardService;
import com.mindhub.homebanking.utils.Utils;
import com.mindhub.homebanking.utils.NumberCardRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class CardController {

    private final CardService cardService;

    public NumberCardRandom numberCardRandom;

    public Utils utils;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping("/cards")
    public Set<CardDTO> getcard(){
        return this.cardService.finAll();
    }

    @RequestMapping("/cards/{id}")
    public CardDTO getcard(@PathVariable long id){
        return this.cardService.findById(id);
    }

    @RequestMapping(path = "/clients/current/cards", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestParam String cardType,
                                           @RequestParam String cardColor, Authentication authentication ) throws ParseException {

        LocalDate createDate = LocalDate.now();

        Client client= clientRepository.findByEmail(authentication.getName());

//        if (client.getCards().size() == 3){
//            return new ResponseEntity<>("Ya cuenta con un maximo de 3 tarjetaas.", HttpStatus.FORBIDDEN);
//        }

        int newCvv;
        newCvv = numberCardRandom.getNumberCvv();

        String newCard;
        newCard = numberCardRandom.getNumberCard();

        LocalDate fetchEnd = createDate.plusYears(5);

        int years = fetchEnd.getYear();
        Month mes = fetchEnd.getMonth();

        String fechaString = (Utils.getConvierteMes(mes) + "/" + years % 100);

        SimpleDateFormat format = new SimpleDateFormat("MM/yy");
        Date thruDate = format.parse(fechaString);

//        cardRepository.save(new Card( CardType.valueOf(cardType.toUpperCase()),  newCard, newCvv,createDate, thruDate,  CardColor.valueOf(cardColor.toUpperCase()), client));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
