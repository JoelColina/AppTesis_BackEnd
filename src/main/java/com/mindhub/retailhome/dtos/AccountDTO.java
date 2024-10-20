package com.mindhub.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AccountDTO {
    private String number;
    private LocalDate creationDate;
    private double balance;
    private boolean enable;
    private String idClient;
    private Set<TransactionDTO> transactions;
}
