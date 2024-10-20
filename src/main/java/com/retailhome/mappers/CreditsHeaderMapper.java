package com.retailhome.mappers;


import com.retailhome.dtos.CreditsHeaderDTO;
import com.retailhome.models.CreditsHeader;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
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
