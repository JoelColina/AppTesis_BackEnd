package com.mindhub.retailhome.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CreditsHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "creditsHeader", fetch = FetchType.EAGER)
    Set<CreditDetail> creditDetails = new HashSet<>();

    private Number requestedAmount;
    private Number quotaNumber;
    private boolean enabled;

    public CreditsHeader(Number requestedAmount, Number quotaNumber, boolean enabled) {
        this.requestedAmount = requestedAmount;
        this.quotaNumber = quotaNumber;
        this.enabled = enabled;
    }

    public CreditsHeader() {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<CreditDetail> getCreditDetails() {
        return creditDetails;
    }

    public void setCreditDetails(Set<CreditDetail> creditDetails) {
        this.creditDetails = creditDetails;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "CreditsHeader{" +
                "client=" + client +
                ", creditDetails=" + creditDetails +
                ", requestedAmount=" + requestedAmount +
                ", quotaNumber=" + quotaNumber +
                ", enabled=" + enabled +
                '}';
    }
}
