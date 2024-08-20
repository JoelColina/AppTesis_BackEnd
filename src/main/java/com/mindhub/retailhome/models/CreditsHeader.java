package com.mindhub.retailhome.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
