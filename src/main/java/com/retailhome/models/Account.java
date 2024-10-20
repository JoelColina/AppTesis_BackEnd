package com.retailhome.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String number;
    private LocalDate creationDate;
    private double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();

    public Account() {
    }

    public Account(long id, String number, LocalDate creationDate, double balance, boolean enable, Client client, Set<Transaction> transactions) {
        this.id = id;
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client = client;
        this.transactions = transactions;
    }

}
