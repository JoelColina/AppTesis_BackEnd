package com.retailhome.mappers;

import com.retailhome.dtos.LoanDTO;
import com.retailhome.dtos.PurchasingDetailDTO;
import com.retailhome.models.Loan;
import com.retailhome.models.PurchasingDetail;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface PurchasingDetailMapper {
    PurchasingDetail toPurchasingDetail (PurchasingDetailDTO purchasingDetailDTO);

    PurchasingDetailDTO toPurchasingDetailDTO (PurchasingDetail purchasingDetail);

    default List<PurchasingDetail> toEntityList(List<PurchasingDetailDTO> purchasingDetailDTOList){
        if (purchasingDetailDTOList == null){
            return  new ArrayList<>();
        }
        return purchasingDetailDTOList.stream().map(this::toPurchasingDetail).collect(Collectors.toList());
    }

    default List<PurchasingDetailDTO> toDtoList(List<PurchasingDetail> purchasingDetailList){
        if (purchasingDetailList == null){
            return  new ArrayList<>();
        }
        return purchasingDetailList.stream().map(this::toPurchasingDetailDTO).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    PurchasingDetail updatePurchasingDetailFromPurchasingDetailDTO(PurchasingDetailDTO purchasingDetailDTO, @MappingTarget PurchasingDetail purchasingDetail);

}
