package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
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

}
