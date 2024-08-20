package com.mindhub.retailhome.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanApplicationDTO {
    private long loanId;
    private double amount;
    private double payments;
    private String toAccountNumber;

    public LoanApplicationDTO(long loanId, double amount, double payments, String toAccountNumber) {
        this.loanId = loanId;
        this.amount = amount;
        this.payments = payments;
        this.toAccountNumber = toAccountNumber;
    }

    public LoanApplicationDTO() {
    }

}