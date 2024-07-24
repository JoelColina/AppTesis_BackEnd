package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.PurchasingDetail;

public class PurchasingDetailDTO {

    private long id;

    private long idClient;
    private long idPurchase;
    private String product;
    private Number amount;
    private Number worth;
    private Number tax;

    public PurchasingDetailDTO(PurchasingDetail purchasingdetail) {
        this.idClient = purchasingdetail.getIdClient();
        this.idPurchase = purchasingdetail.getIdPurchase();
        this.product = purchasingdetail.getProduct();
        this.amount = purchasingdetail.getAmount();
        this.worth = purchasingdetail.getWorth();
        this.tax = purchasingdetail.getTax();
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
}
