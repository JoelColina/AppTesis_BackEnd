package com.retailhome.mappers;


import com.retailhome.dtos.PurchasingHeaderDTO;

import com.retailhome.models.PurchasingHeader;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface PurchasingHeaderMapper {
    PurchasingHeader toPurchasingHeader (PurchasingHeaderDTO purchasingHeaderDTO);

    PurchasingHeaderDTO toPurchasingHeaderDTO (PurchasingHeader purchasingHeader);
    
    default List<PurchasingHeader> toEntityList(List<PurchasingHeaderDTO> purchasingHeaderlDTOList){
        if (purchasingHeaderlDTOList == null){
            return  new ArrayList<>();
        }
        return purchasingHeaderlDTOList.stream().map(this::toPurchasingHeader).collect(Collectors.toList());
    }

    default List<PurchasingHeaderDTO> toDtoList(List<PurchasingHeader> purchasingHeaderList){
        if (purchasingHeaderList == null){
            return  new ArrayList<>();
        }
        return purchasingHeaderList.stream().map(this::toPurchasingHeaderDTO).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    PurchasingHeader updatePurchasingHeaderFromPurchasingHeaderDTO(PurchasingHeaderDTO purchasingHeaderlDTO, @MappingTarget PurchasingHeader purchasingHeader);

}
