package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;

import java.time.LocalDate;
import java.util.Date;

public class CardDTO {
    private long id;
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

    public CardDTO(Card card) {
//        this.idClient = card.getIdClient();
        this.type = card.getType();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.validDate = card.getValidDate();
        this.thruDate = card.getThruDate();
        this.cardHolder = card.getCardHolder();
        this.color = card.getColor();
        this.totalLimit = card.getTotalLimit();
        this.quotaUsed = card.getQuotaUsed();
        this.balanceQuota = card.getBalanceQuota();
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
}
