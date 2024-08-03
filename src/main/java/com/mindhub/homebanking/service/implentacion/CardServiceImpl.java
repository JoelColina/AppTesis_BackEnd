package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.AddressType;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.service.CardService;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Set<CardDTO> finAll() {
        return this.cardRepository.findAll().stream().map(CardDTO::new).collect(Collectors.toSet());
    }

    @Override
    public CardDTO finById(Long id) {
        return this.cardRepository.findById(id).map(CardDTO::new).orElse(null);
    }

    @Override
    public CardDTO save(CardDTO cardDTO) {
        return null;
    }

    @Override
    public boolean delete(CardDTO cardDTO) {
        return false;
    }

    @Override
    public ResponseEntity<?> update(CardDTO cardDTO) {
        return null;
    }

}
