package com.retailhome.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class CreditDto {

    private  CreditsHeaderDTO creditsHeaderDTO;
    private  Set<CreditDetailDTO> creditDetailDTOS = new HashSet<CreditDetailDTO>();
}
