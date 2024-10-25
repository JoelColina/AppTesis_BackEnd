package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.CreditsHeaderDTO;
import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import com.mindhub.retailhome.models.CreditsHeader;
import com.mindhub.retailhome.models.PurchasingHeader;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface CreditsHeaderMapper {

    CreditsHeader toCreditsHeader (CreditsHeaderDTO creditsHeaderDto);

    CreditsHeaderDTO toCreditsHeaderDto (CreditsHeader creditsHeader);

    default List<CreditsHeaderDTO> toDoList (List<CreditsHeader> purchasingHeaderList){
        if (purchasingHeaderList == null) {
            return new ArrayList<>();
        }
        return purchasingHeaderList.stream().map(this::toCreditsHeaderDto).collect(Collectors.toList());
    }

    default List<CreditsHeader> toEntityList (List<CreditsHeaderDTO> creditsHeaderDtoList){
        if (creditsHeaderDtoList == null) {
            return new ArrayList<>();
        }
        return creditsHeaderDtoList.stream().map(this::toCreditsHeader).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    CreditsHeader updateCreditsHeaderFromCreditsHeaderDto(CreditsHeaderDTO creditsHeaderDto, @MappingTarget PurchasingHeader purchasingHeader);
}
