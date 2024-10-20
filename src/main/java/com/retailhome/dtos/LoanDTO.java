package com.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoanDTO {
    private long id;
    private String name;
    private int maxAmount;
    private List<Integer> payments;
    private List<ClientLoanDTO> clientLoans;

}
