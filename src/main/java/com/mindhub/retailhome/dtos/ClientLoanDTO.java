package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.ClientLoan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientLoanDTO {
    private long id;
    private double amount;
    private double payments;
    private long loanid;
    private String name;

    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
//        this.loanid = clientLoan.getLoan().getId();
//        this.name = clientLoan.getLoan().getName();
    }

}
