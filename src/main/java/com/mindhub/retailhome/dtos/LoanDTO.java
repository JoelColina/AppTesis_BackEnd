package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.Loan;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoanDTO {
    private long id;
    private String name;
    private int maxAmount;
    private List<Integer> payments;
    private List<ClientLoanDTO> clientLoans;

    public LoanDTO(Loan loan) {
        this.id        = loan.getId();
        this.name      = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments  = loan.getPayments();

//        this.clientLoans = loan.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toList());
    }

    public LoanDTO() {
    }

}
