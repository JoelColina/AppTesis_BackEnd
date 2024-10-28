package com.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Component
public class LoanApplicationDTO {
    private long loanId;
    private double amount;
    private double payments;
    private String toAccountNumber;



}