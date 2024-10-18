package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.CreditsHeader;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CreditsHeaderDTO {

    private Number requestedAmount;
    private Number quotaNumber;
    private long idClient;
    private boolean enabled;

}
