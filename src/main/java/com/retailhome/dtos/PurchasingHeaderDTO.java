package com.retailhome.dtos;

import com.retailhome.utils.enums.AddressType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Data
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
    private long idClient;


}
