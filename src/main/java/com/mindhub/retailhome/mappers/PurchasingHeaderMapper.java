package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.PurchasingHeaderDTO;
import com.mindhub.retailhome.models.PurchasingHeader;

import java.util.Optional;

public interface PurchasingHeaderMapper {
    PurchasingHeader purchasingHeaderDtoToPurchasingHeader (PurchasingHeaderDTO purchasingHeaderDTO);

    PurchasingHeaderDTO purchasingHeaderToPurchasingHeaderDto (Optional<PurchasingHeader> purchasingHeader);

}
