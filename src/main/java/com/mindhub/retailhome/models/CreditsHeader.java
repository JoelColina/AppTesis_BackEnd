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
    @JoinColumn(name = "idClient")
    private Client client;

    @OneToMany(mappedBy = "creditsHeader", fetch = FetchType.EAGER)
    Set<CreditDetail> creditDetails = new HashSet<>();

    private Number requestedAmount;
    private Number quotaNumber;
    private boolean enabled;
    private String idClient;

    public CreditsHeader(Number requestedAmount, Number quotaNumber, boolean enabled, Client client) {
        this.requestedAmount = requestedAmount;
        this.quotaNumber = quotaNumber;
        this.enabled = enabled;
        this.idClient = client.getIdClient();
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
