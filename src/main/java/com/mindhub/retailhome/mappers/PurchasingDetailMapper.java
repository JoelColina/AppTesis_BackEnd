package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.PurchasingDetailDTO;
import com.mindhub.retailhome.models.PurchasingDetail;

public interface PurchasingDetailMapper {
    PurchasingDetail purchasingDetailDtoToPurchasingDetail (PurchasingDetailDTO purchasingDetailDTO);

    PurchasingDetailDTO purchasingDetailToPurchasingDetailDto (PurchasingDetail purchasingDetail);
}
