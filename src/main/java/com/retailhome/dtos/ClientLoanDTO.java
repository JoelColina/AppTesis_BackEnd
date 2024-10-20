package com.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
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
