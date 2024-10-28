package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.AccountDTO;
import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import com.mindhub.retailhome.models.Account;
import com.mindhub.retailhome.models.PurchasingHeader;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface PurchasingHeaderMapper {
    PurchasingHeader toPurchasingHeader (PurchasingHeaderDTO purchasingHeaderDTO);

    PurchasingHeaderDTO toPurchasingHeaderDto (PurchasingHeader purchasingHeader);

    default List<PurchasingHeaderDTO> toDoList (List<PurchasingHeader> purchasingHeaderList){
        if (purchasingHeaderList == null) {
            return new ArrayList<>();
        }
        return purchasingHeaderList.stream().map(this::toPurchasingHeaderDto).collect(Collectors.toList());
    }

    default List<PurchasingHeader> toEntityList (List<PurchasingHeaderDTO> purchasingHeaderDTOList){
        if (purchasingHeaderDTOList == null) {
            return new ArrayList<>();
        }
        return purchasingHeaderDTOList.stream().map(this::toPurchasingHeader).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    PurchasingHeader updatePurchasingHeaderFromPurchasingHeaderDto(PurchasingHeaderDTO purchasingHeaderDto, @MappingTarget PurchasingHeader purchasingHeader);
}
