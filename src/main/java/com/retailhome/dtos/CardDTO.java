package com.retailhome.dtos;

import com.retailhome.utils.enums.CardColor;
import com.retailhome.utils.enums.CardType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
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
    private Long idClient;


}
