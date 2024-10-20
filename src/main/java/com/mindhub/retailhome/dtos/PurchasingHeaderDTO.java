package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.utils.enums.AddressType;
import com.mindhub.retailhome.models.PurchasingHeader;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PurchasingHeaderDTO {

    private String trade;
    private String product;
    private Number numberBuy;
    private Number sku;
    private Date purchaseDate;
    private Number amount;
    private Number worth;
    private Number nroQuotes;
    private Number totalValue;
    private String cardType;
    private AddressType type;
    private String deliverDate;
    private String deliveryAddress;
    private String retiredBy;
    private boolean enabled;

}
