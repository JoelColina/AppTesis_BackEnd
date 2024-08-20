package com.mindhub.retailhome.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PurchasingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_id")
    private PurchasingHeader purchasingHeader;

    private String product;
    private Number amount;
    private Number worth;
    private Number tax;

    public PurchasingDetail(String product, Number amount, Number worth, Number tax) {
        this.product = product;
        this.amount = amount;
        this.worth = worth;
        this.tax = tax;
    }

    public PurchasingDetail() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public Number getTax() {
        return tax;
    }

    public void setTax(Number tax) {
        this.tax = tax;
    }

    public PurchasingHeader getPurchasingHeader() {
        return purchasingHeader;
    }

    public void setPurchasingHeader(PurchasingHeader purchasingHeader) {
        this.purchasingHeader = purchasingHeader;
    }

    @Override
    public String toString() {
        return "PurchasingDetail{" +
                ", product='" + product + '\'' +
                ", amount=" + amount +
                ", worth=" + worth +
                ", tax=" + tax +
                '}';
    }
}
