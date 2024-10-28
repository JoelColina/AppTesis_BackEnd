package com.retailhome.models;

import com.retailhome.utils.enums.CardColor;
import com.retailhome.utils.enums.CardType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
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



}
