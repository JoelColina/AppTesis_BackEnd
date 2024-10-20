package com.mindhub.retailhome.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "purchasing_details")
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

    public PurchasingDetail(long id, PurchasingHeader purchasingHeader, String product, Number amount, Number worth, Number tax) {
        this.id = id;
        this.purchasingHeader = purchasingHeader;
        this.product = product;
        this.amount = amount;
        this.worth = worth;
        this.tax = tax;
    }

    public PurchasingDetail() {
    }
}
