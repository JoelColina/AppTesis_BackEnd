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

    CreditsHeader toCreditsHeader (CreditsHeaderDTO creditsHeaderDTO);

    CreditsHeaderDTO toCreditsHeaderDTO (CreditsHeader creditsHeader);

    default List<CreditsHeader> toEntityList(List<CreditsHeaderDTO> creditsHeaderDTOList){
        if (creditsHeaderDTOList == null){
            return  new ArrayList<>();
        }
        return creditsHeaderDTOList.stream().map(this::toCreditsHeader).collect(Collectors.toList());
    }

    default List<CreditsHeaderDTO> toDTOList(List<CreditsHeader> creditsHeaderList){
        if (creditsHeaderList == null){
            return  new ArrayList<>();
        }
        return creditsHeaderList.stream().map(this::toCreditsHeaderDTO).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    CreditsHeader updateCreditsHeaderFromCreditsHeaderDTO(CreditsHeaderDTO creditsHeaderDTO, @MappingTarget CreditsHeader creditsHeader );
}
