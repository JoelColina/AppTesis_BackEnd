package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransactionDTO {
    private long id;
    private String type;
    private String description;
    private LocalDate creationDate;
    private double amount;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType().toString();
        this.description = transaction.getDescription();
        this.creationDate = transaction.getDate();
        this.amount = transaction.getAmount();
    }

    public TransactionDTO() {
    }

}
