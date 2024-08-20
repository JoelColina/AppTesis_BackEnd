package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.CreditDetailDTO;
import com.mindhub.retailhome.models.CreditDetail;

public interface CreditDetailMapper {

    CreditDetail creditDetailDtoToCreditDetail (CreditDetailDTO creditDetailDTO);

    CreditDetailDTO creditDetailToCreditDetailDto (CreditDetail creditDetail);

}
