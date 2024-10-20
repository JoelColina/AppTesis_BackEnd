package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.Card;
import com.mindhub.retailhome.utils.enums.CardColor;
import com.mindhub.retailhome.utils.enums.CardType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
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
    private long idClient;
}
