package com.mindhub.retailhome.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
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
    private Long idClient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public Card(CardType type, String number, int cvv, LocalDate validDate, Date thruDate, String cardHolder, CardColor color, Number totalLimit, Number quotaUsed, Number balanceQuota, boolean enabled) {

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
        this.idClient = client.getId();
    }

    public Card() {
    }


}
