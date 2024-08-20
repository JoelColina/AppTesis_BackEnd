package com.mindhub.retailhome.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
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

    private String password;

    public Client(String names, String lastName, String motherLastName, String ruth, Date birthDate, Number telephoneNumber, String email, Number totalLimit, Number debtAccount, Number availableSpace, String password) {
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
        this.password = password;
    }

    public Client() {
    }

    //    @JsonIgnore
//    public Set<ClientLoan> getClientLoans() {
//        return clientLoans;
//    }

    public void addClientLoans(ClientLoan clientLoan){
        clientLoan.setClient(this);
        clientLoans.add(clientLoan);
    }

    public List<Loan> getLoans(){
        return clientLoans.stream().map(ClientLoan::getLoan).collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "Client{" +
                "names='" + names + '\'' +
                ", lastName='" + lastName + '\'' +
                ", motherLastName='" + motherLastName + '\'' +
                ", ruth='" + ruth + '\'' +
                ", birthDate=" + birthDate +
                ", telephoneNumber=" + telephoneNumber +
                ", email='" + email + '\'' +
                ", totalLimit=" + totalLimit +
                ", debtAccount=" + debtAccount +
                ", availableSpace=" + availableSpace +
                ", password='" + password + '\'' +
                '}';
    }
}

