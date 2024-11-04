package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.CreditsHeader;
import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class CreditsHeaderDTO {

    private Number requestedAmount;
    private Number quotaNumber;
    private Long idClient;
    private boolean enabled;

}
