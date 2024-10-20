package com.retailhome.mappers;

import com.retailhome.dtos.CardDTO;
import com.retailhome.models.Card;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface CardMapper {

    Card toCard(CardDTO cardDTO);

    CardDTO toCardDTO(Card card);

    default List<CardDTO> toDtoList(List<Card> cardList) {
        if (cardList == null) {
            return new ArrayList<>();
        }
        return cardList.stream().map(this::toCardDTO).collect(Collectors.toList());
    }

    default List<Card> toEntityList(List<CardDTO> cardDTOList) {
        if (cardDTOList == null) {
            return new ArrayList<>();
        }
        return cardDTOList.stream().map(this::toCard).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Card updateCardFromCardDto(CardDTO cardDTO, @MappingTarget Card card);
}