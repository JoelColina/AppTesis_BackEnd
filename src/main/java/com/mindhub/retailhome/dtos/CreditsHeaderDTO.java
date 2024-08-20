package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.CreditsHeader;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Number getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(Number requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public Number getQuotaNumber() {
        return quotaNumber;
    }

    public void setQuotaNumber(Number quotaNumber) {
        this.quotaNumber = quotaNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
