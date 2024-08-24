package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.Card;
import com.mindhub.retailhome.models.CardColor;
import com.mindhub.retailhome.models.CardType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class CardDTO {

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
    private Long idClient;

    public CardDTO(Card card) {
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
        this.enabled = card.isEnabled();
        this.idClient = card.getIdClient();
    }

    public CardDTO() {
    }

}
