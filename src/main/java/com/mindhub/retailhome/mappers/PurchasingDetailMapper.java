package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.PurchasingDetailDTO;
import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import com.mindhub.retailhome.models.PurchasingDetail;
import com.mindhub.retailhome.models.PurchasingHeader;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface PurchasingDetailMapper {
    PurchasingDetail toPurchasingDetail (PurchasingDetailDTO purchasingDetailDTO);

    PurchasingDetailDTO toPurchasingDetailDto (PurchasingDetail purchasingDetail);

    default List<PurchasingDetailDTO> toDoList (List<PurchasingDetail> PurchasingDetailList){
        if (PurchasingDetailList == null) {
            return new ArrayList<>();
        }
        return PurchasingDetailList.stream().map(this::toPurchasingDetailDto).collect(Collectors.toList());
    }

    default List<PurchasingDetail> toEntityList (List<PurchasingDetailDTO> purchasingDetailDTOList){
        if (purchasingDetailDTOList == null) {
            return new ArrayList<>();
        }
        return purchasingDetailDTOList.stream().map(this::toPurchasingDetail).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    PurchasingDetail updatePurchasingDetailFromPurchasingDetailDto(PurchasingDetailDTO purchasingDetailDTO, @MappingTarget PurchasingDetail purchasingDetail);

}
