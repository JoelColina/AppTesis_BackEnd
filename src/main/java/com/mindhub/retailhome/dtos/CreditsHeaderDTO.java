package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.CreditsHeader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditsHeaderDTO {
    private long id;

    private Number requestedAmount;
    private Number quotaNumber;
    private boolean enabled;

    public CreditsHeaderDTO(CreditsHeader creditsheader) {
        this.requestedAmount = creditsheader.getRequestedAmount();
        this.quotaNumber = creditsheader.getQuotaNumber();
        this.enabled = creditsheader.isEnabled();
    }

    public CreditsHeaderDTO() {
    }

}
