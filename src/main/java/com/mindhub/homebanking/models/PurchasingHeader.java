package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PurchasingHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private long idClient;
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

    public PurchasingHeader(long idClient, String trade, String product, Number numberBuy, Number sku, Date purchaseDate, Number amount, Number worth, Number nroQuotes, Number totalValue, String cardType, AddressType type, String deliverDate, String deliveryAddress, String retiredBy) {
        this.idClient = idClient;
        this.trade = trade;
        this.product = product;
        this.numberBuy = numberBuy;
        this.sku = sku;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
        this.worth = worth;
        this.nroQuotes = nroQuotes;
        this.totalValue = totalValue;
        this.cardType = cardType;
        this.type = type;
        this.deliverDate = deliverDate;
        this.deliveryAddress = deliveryAddress;
        this.retiredBy = retiredBy;
    }

    public PurchasingHeader() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
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

    @Override
    public String toString() {
        return "purchasingHeader{" +
                "trade='" + trade + '\'' +
                ", product='" + product + '\'' +
                ", numberBuy=" + numberBuy +
                ", sku=" + sku +
                ", purchaseDate=" + purchaseDate +
                ", amount=" + amount +
                ", worth=" + worth +
                ", nroQuotes=" + nroQuotes +
                ", totalValue=" + totalValue +
                ", cardType='" + cardType + '\'' +
                ", type=" + type +
                ", deliverDate='" + deliverDate + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", retiredBy='" + retiredBy + '\'' +
                ", idClient=" + idClient +
                '}';
    }
}
