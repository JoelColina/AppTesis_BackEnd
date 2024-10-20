package com.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@Data
public class TransactionDTO {
    private long id;
    private String type;
    private String description;
    private LocalDate creationDate;
    private double amount;
}
