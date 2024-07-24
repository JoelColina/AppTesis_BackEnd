package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.CreditsHeader;

public class CreditsHeaderDTO {
    private long id;

    private long idClient;
    private Number requestedAmount;
    private Number quotaNumber;

    public CreditsHeaderDTO(CreditsHeader creditsheader) {
        this.idClient = creditsheader.getIdClient();
        this.requestedAmount = creditsheader.getRequestedAmount();
        this.quotaNumber = creditsheader.getQuotaNumber();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
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
}
