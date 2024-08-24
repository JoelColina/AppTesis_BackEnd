package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.AddressType;
import com.mindhub.retailhome.models.PurchasingHeader;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
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

    public PurchasingHeaderDTO(PurchasingHeader purchasingHeader) {
        this.trade = purchasingHeader.getTrade();
        this.product = purchasingHeader.getProduct();
        this.numberBuy = purchasingHeader.getNumberBuy();
        this.sku = purchasingHeader.getSku();
        this.purchaseDate = purchasingHeader.getPurchaseDate();
        this.amount = purchasingHeader.getAmount();
        this.worth = purchasingHeader.getWorth();
        this.nroQuotes = purchasingHeader.getNroQuotes();
        this.totalValue = purchasingHeader.getTotalValue();
        this.cardType = purchasingHeader.getCardType();
        this.type = purchasingHeader.getType();
        this.deliverDate = purchasingHeader.getDeliverDate();
        this.deliveryAddress = purchasingHeader.getDeliveryAddress();
        this.retiredBy = purchasingHeader.getRetiredBy();
        this.enabled = purchasingHeader.isEnabled();
        this.idClient = purchasingHeader.getIdClient();
    }

    public PurchasingHeaderDTO() {
    }

}
