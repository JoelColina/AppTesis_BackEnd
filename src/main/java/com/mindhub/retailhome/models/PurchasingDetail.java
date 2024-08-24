package com.mindhub.retailhome.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
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
    private long idPurchasing;

    public PurchasingDetail(String product, Number amount, Number worth, Number tax) {
        this.product = product;
        this.amount = amount;
        this.worth = worth;
        this.tax = tax;
        this.idPurchasing = purchasingHeader.getId();
    }

    public PurchasingDetail() {
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
