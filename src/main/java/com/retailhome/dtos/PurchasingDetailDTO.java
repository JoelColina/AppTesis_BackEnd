package com.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PurchasingDetailDTO {

    private String product;
    private Number amount;
    private Number worth;
    private Number tax;
    private long idPurchasing;


}
