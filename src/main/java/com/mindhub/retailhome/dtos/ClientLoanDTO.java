package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.ClientLoan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientLoanDTO {

    private double amount;
    private double payments;
    private long idClient;
    private long idLoans;
    private String name;

    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
        this.idClient = clientLoan.getIdClient();
        this.idLoans = clientLoan.getIdLoans();
    }

}
