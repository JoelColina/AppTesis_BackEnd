package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.CardDTO;
import com.mindhub.retailhome.models.Card;

import java.util.Optional;

public interface CardMapper {

    Card cardDtoToCard (CardDTO cardDTO);

    CardDTO cardToCardDto (Optional<Card> card);
}
