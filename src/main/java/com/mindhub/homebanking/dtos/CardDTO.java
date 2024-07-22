package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;

import java.time.LocalDate;
import java.util.Date;

public class CardDTO {
    private long id;

    private CardType type;
    private String number;
    private int cvv;
    private LocalDate fecValida;
    private Date fecTemino;
    private String cardHolder;
    private CardColor color;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.type = card.getType();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.fecValida = card.getFecValida();
        this.fecTemino = card.getThruDate();
        this.cardHolder = card.getCardHolder();
        this.color = card.getColor();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public LocalDate getFecValida() {
        return fecValida;
    }

    public void setFecValida(LocalDate fecValida) {
        this.fecValida = fecValida;
    }

    public Date getFecTemino() {
        return fecTemino;
    }

    public void setFecTemino(Date fecTemino) {
        this.fecTemino = fecTemino;
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
}
