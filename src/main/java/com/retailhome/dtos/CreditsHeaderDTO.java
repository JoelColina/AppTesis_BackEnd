package com.retailhome.dtos;

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
