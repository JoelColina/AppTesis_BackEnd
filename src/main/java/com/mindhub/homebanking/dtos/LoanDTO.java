package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Loan;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        this.clientLoans = loan.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public List<ClientLoanDTO> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(List<ClientLoanDTO> clientLoans) {
        this.clientLoans = clientLoans;
    }
}
