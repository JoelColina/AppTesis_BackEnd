package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.AddressType;
import com.mindhub.homebanking.models.PurchasingHeader;
import com.mindhub.homebanking.repositories.PurchasingHeaderRepository;
import com.mindhub.homebanking.service.implentacion.CardServiceImpl;

import java.util.Date;

public class PurchasingHeaderDTO {

    private long id;

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
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Number getNumberBuy() {
        return numberBuy;
    }

    public void setNumberBuy(Number numberBuy) {
        this.numberBuy = numberBuy;
    }

    public Number getSku() {
        return sku;
    }

    public void setSku(Number sku) {
        this.sku = sku;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Number getAmount() {
        return amount;
    }

    public void setAmount(Number amount) {
        this.amount = amount;
    }

    public Number getWorth() {
        return worth;
    }

    public void setWorth(Number worth) {
        this.worth = worth;
    }

    public Number getNroQuotes() {
        return nroQuotes;
    }

    public void setNroQuotes(Number nroQuotes) {
        this.nroQuotes = nroQuotes;
    }

    public Number getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Number totalValue) {
        this.totalValue = totalValue;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getRetiredBy() {
        return retiredBy;
    }

    public void setRetiredBy(String retiredBy) {
        this.retiredBy = retiredBy;
    }
}
