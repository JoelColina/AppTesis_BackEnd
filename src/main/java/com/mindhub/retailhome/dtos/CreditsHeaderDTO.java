package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.CreditsHeader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditsHeaderDTO {

    private Number requestedAmount;
    private Number quotaNumber;
    private String idClient;
    private boolean enabled;

    public CreditsHeaderDTO(CreditsHeader creditsheader) {
        this.requestedAmount = creditsheader.getRequestedAmount();
        this.quotaNumber = creditsheader.getQuotaNumber();
        this.enabled = creditsheader.isEnabled();
        this.idClient = creditsheader.getIdClient();
    }

    public CreditsHeaderDTO() {
    }

    public CreditsHeaderDTO(double creditsheader) {
    }
}
