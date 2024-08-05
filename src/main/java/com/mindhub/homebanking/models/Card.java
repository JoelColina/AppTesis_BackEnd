package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
//
//    private long idClient;
    private CardType type;
    private String number;
    private int cvv;
    private LocalDate validDate;
    private Date thruDate;
    private String cardHolder;
    private CardColor color;
    private Number totalLimit;
    private Number quotaUsed;
    private Number balanceQuota;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public Card(CardType type, String number, int cvv, LocalDate validDate, Date thruDate, String cardHolder, CardColor color, Number totalLimit, Number quotaUsed, Number balanceQuota, boolean enabled, Client client) {
//        this.idClient = idClient;
        this.type = type;
        this.number = number;
        this.cvv = cvv;
        this.validDate = validDate;
        this.thruDate = thruDate;
        this.cardHolder = cardHolder;
        this.color = color;
        this.totalLimit = totalLimit;
        this.quotaUsed = quotaUsed;
        this.balanceQuota = balanceQuota;
        this.enabled = enabled;
//        this.client = client;
    }

    public Card() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public long getIdClient() {
//        return idClient;
//    }
//
//    public void setIdClient(long idClient) {
//        this.idClient = idClient;
//    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getValidDate() {
        return validDate;
    }

    public void setValidDate(LocalDate validDate) {
        this.validDate = validDate;
    }

    public Date getThruDate() {
        return thruDate;
    }

    public void setThruDate(Date thruDate) {
        this.thruDate = thruDate;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public Number getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Number totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Number getQuotaUsed() {
        return quotaUsed;
    }

    public void setQuotaUsed(Number quotaUsed) {
        this.quotaUsed = quotaUsed;
    }

    public Number getBalanceQuota() {
        return balanceQuota;
    }

    public void setBalanceQuota(Number balanceQuota) {
        this.balanceQuota = balanceQuota;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
