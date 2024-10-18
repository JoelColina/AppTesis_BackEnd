package com.mindhub.retailhome.models;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "client_loan")
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double amount;
    private double payments;
    private long idLoans;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    public ClientLoan() {
    }

    public ClientLoan(long id, double amount, double payments, long idClient, long idLoans, Client client, Loan loan) {
        this.id = id;
        this.amount = amount;
        this.payments = payments;
        this.idLoans = idLoans;
        this.client = client;
        this.loan = loan;
    }
}
