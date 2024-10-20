package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.ClientLoan;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ClientLoanDTO {
    private double amount;
    private double payments;
    private long idClient;
    private long idLoans;
    private String name;
}
