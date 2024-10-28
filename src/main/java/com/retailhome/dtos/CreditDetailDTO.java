package com.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Component
public class CreditDetailDTO {

    private Date dateExpiration;
    private String quotaStatus;
    private Date payDay;
    private long CreditsHeader;


}
