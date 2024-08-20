package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.CardDTO;
import com.mindhub.retailhome.models.Card;

public interface CardMapper {

    Card cardDtoToCard (CardDTO cardDTO);

    CardDTO cardToCardDto (Card card);
}
