package com.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CreditDetailDTO {

    private Date dateExpiration;
    private String quotaStatus;
    private Date payDay;
    private long CreditsHeader;

    public CreditDetailDTO(Date dateExpiration, String quotaStatus, Date payDay, long creditsHeader) {
        this.dateExpiration = dateExpiration;
        this.quotaStatus = quotaStatus;
        this.payDay = payDay;
        CreditsHeader = creditsHeader;
    }

    public CreditDetailDTO() {
    }

}
