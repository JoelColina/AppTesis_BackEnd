package com.mindhub.retailhome.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String names;
    private String lastName;
    private String motherLastName;
    private String ruth;
    private Date   birthDate;
    private Number telephoneNumber;
    private String email;
    private Number totalLimit;
    private Number debtAccount;
    private Number availableSpace;
    private long idClient;
    private boolean enabled;
    private String password;
    private String username ;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<Addresses> addresses = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<CreditsHeader> creditsHeaders = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<PurchasingHeader> purchasingHeaders = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<Card> cards = new HashSet<>();

    public Client(long id, String names, String lastName, String motherLastName,
                  String ruth, Date birthDate, Number telephoneNumber, String email,
                  Number totalLimit, Number debtAccount, Number availableSpace,
                  long idClient, String password, String username,
                  Set<Addresses> addresses, Set<CreditsHeader> creditsHeaders,
                  Set<PurchasingHeader> purchasingHeaders, Set<ClientLoan> clientLoans,
                  Set<Card> cards,boolean enabled) {

        this.id = id;
        this.names = names;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.ruth = ruth;
        this.birthDate = birthDate;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.totalLimit = totalLimit;
        this.debtAccount = debtAccount;
        this.availableSpace = availableSpace;
        this.idClient = idClient;
        this.password = password;
        this.username = username;
        this.addresses = addresses;
        this.creditsHeaders = creditsHeaders;
        this.purchasingHeaders = purchasingHeaders;
        this.clientLoans = clientLoans;
        this.cards = cards;
        this.enabled = enabled;
    }

    public Client() {
    }
}

