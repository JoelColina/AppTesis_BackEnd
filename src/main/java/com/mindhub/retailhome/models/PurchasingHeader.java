package com.mindhub.retailhome.models;

import com.mindhub.retailhome.utils.enums.AddressType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "purchasing_header")
public class PurchasingHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "purchasingHeader", fetch = FetchType.EAGER)
    Set<PurchasingDetail> purchasingDetails = new HashSet<>();

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

    public PurchasingHeader(long id, Client client, Set<PurchasingDetail> purchasingDetails, String trade, String product, Number numberBuy, Number sku, Date purchaseDate, Number amount, Number worth, Number nroQuotes, Number totalValue, String cardType, AddressType type, String deliverDate, String deliveryAddress, String retiredBy, boolean enabled) {
        this.id = id;
        this.client = client;
        this.purchasingDetails = purchasingDetails;
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
        this.enabled = enabled;
    }

    public PurchasingHeader() {
    }



}
