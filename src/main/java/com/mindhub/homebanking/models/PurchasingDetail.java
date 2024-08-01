package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PurchasingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private long idPurchase;
    private String product;
    private Number amount;
    private Number worth;
    private Number tax;

    public PurchasingDetail( long idPurchase, String product, Number amount, Number worth, Number tax) {
        this.idPurchase = idPurchase;
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

    public long getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(long idPurchase) {
        this.idPurchase = idPurchase;
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

    @Override
    public String toString() {
        return "PurchasingDetail{" +
                ", idPurchase=" + idPurchase +
                ", product='" + product + '\'' +
                ", amount=" + amount +
                ", worth=" + worth +
                ", tax=" + tax +
                '}';
    }
}
