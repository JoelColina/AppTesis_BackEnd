package com.mindhub.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class LoanApplicationDTO {
    private long loanId;
    private double amount;
    private double payments;
    private String toAccountNumber;
}