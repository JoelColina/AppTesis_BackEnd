package com.mindhub.retailhome.models;

import com.mindhub.retailhome.utils.enums.CardColor;
import com.mindhub.retailhome.utils.enums.CardType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

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

    public Card(long id, CardType type, String number, int cvv, LocalDate validDate, Date thruDate, String cardHolder, CardColor color, Number totalLimit, Number quotaUsed, Number balanceQuota, boolean enabled) {

        this.id = id;
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
    }

    public Card() {
    }


}
