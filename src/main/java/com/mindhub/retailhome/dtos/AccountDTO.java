package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.Account;

import java.time.LocalDate;
import java.util.Set;

public class AccountDTO {
    private long id;
    private String number;
    private LocalDate creationDate;
    private double balance;
    private boolean enable;

    private Set<TransactionDTO> transactions;

    public AccountDTO(Account account){
       this.id = account.getId();
       this.number = account.getNumber();
       this.creationDate = account.getCreationDate();
       this.balance = account.getBalance();
       this.enable = account.isEnable();


//       this.transactions = account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());

    }

    public AccountDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
