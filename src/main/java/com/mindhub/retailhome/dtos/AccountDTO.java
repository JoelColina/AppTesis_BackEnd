package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.Account;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class AccountDTO {
    private String number;
    private LocalDate creationDate;
    private double balance;
    private boolean enable;
    private Long idClient;
    private Set<TransactionDTO> transactions;
}
