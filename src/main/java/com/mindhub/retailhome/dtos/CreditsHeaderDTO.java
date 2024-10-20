package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.CreditsHeader;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CreditsHeaderDTO {

    private Number requestedAmount;
    private Number quotaNumber;
    private String idClient;
    private boolean enabled;

}
