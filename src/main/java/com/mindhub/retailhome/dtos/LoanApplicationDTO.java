package com.mindhub.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LoanApplicationDTO {
    private long loanId;
    private double amount;
    private double payments;
    private String toAccountNumber;
}