package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.CreditDetail;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CreditDetailDTO {

    private double amount;
    private Date dateExpiration;
    private String quotaStatus;
    private Date payDay;
    private long idCredit;
}
