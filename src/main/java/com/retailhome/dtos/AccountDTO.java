package com.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Component
public class AccountDTO {

    private String number;
    private LocalDate creationDate;
    private double balance;
    private Long idClient;
    private Set<TransactionDTO> transactions;

}
