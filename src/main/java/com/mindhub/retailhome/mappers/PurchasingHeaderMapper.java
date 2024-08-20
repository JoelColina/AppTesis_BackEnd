package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import com.mindhub.retailhome.models.PurchasingHeader;

public interface PurchasingHeaderMapper {
    PurchasingHeader purchasingHeaderDtoToPurchasingHeader (PurchasingHeaderDTO purchasingHeaderDTO);

    PurchasingHeaderDTO purchasingHeaderToPurchasingHeaderDto (PurchasingHeader purchasingHeader);

}
