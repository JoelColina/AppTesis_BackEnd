package com.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Component
public class PurchasingDetailDTO {

    private String product;
    private Number amount;
    private Number worth;
    private Number tax;
    private long idPurchasing;


}
