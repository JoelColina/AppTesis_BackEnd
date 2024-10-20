package com.retailhome.mappers;

import com.retailhome.dtos.CreditDetailDTO;
import com.retailhome.models.CreditDetail;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface CreditDetailMapper {

    CreditDetail toCreditDetail (CreditDetailDTO creditDetailDTO);

    CreditDetailDTO toCreditDetailDto (CreditDetail creditDetail);

    default List<CreditDetail> toEntityList(List<CreditDetailDTO> creditDetailDTOList){
        if (creditDetailDTOList == null){
            return  new ArrayList<>();
        }
        return creditDetailDTOList.stream().map(this::toCreditDetail).collect(Collectors.toList());
    }

    default List<CreditDetailDTO> toDtoList(List<CreditDetail> creditDetailList){
        if (creditDetailList == null){
            return  new ArrayList<>();
        }
        return creditDetailList.stream().map(this::toCreditDetailDto).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    CreditDetail updateCreditDetailFromCreditDetailDto(CreditDetailDTO creditDetailDTO, @MappingTarget CreditDetail creditDetail);

}
