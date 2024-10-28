package com.retailhome.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
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
    private String idClient;
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


}

