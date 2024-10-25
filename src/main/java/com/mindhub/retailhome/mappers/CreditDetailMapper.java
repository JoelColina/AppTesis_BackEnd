package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.CreditDetailDTO;
import com.mindhub.retailhome.dtos.PurchasingDetailDTO;
import com.mindhub.retailhome.models.CreditDetail;
import com.mindhub.retailhome.models.PurchasingDetail;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface CreditDetailMapper {

    CreditDetail toCreditDetail (CreditDetailDTO creditDetailDTO);

    CreditDetailDTO toCreditDetailDto (CreditDetail creditDetail);
    default List<CreditDetailDTO> toDoList (List<CreditDetail> creditDetailList){
        if (creditDetailList == null) {
            return new ArrayList<>();
        }
        return creditDetailList.stream().map(this::toCreditDetailDto).collect(Collectors.toList());
    }

    default List<CreditDetail> toEntityList (List<CreditDetailDTO> creditDetailListDtoList){
        if (creditDetailListDtoList == null) {
            return new ArrayList<>();
        }
        return creditDetailListDtoList.stream().map(this::toCreditDetail).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    PurchasingDetail updateCreditDetailFromCreditDetailDto(CreditDetailDTO creditDetailDTO, @MappingTarget CreditDetail CreditDetail);

}
