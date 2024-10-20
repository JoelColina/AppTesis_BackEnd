package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.Transaction;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionDTO {
    private long id;
    private String type;
    private String description;
    private LocalDate creationDate;
    private double amount;
}
