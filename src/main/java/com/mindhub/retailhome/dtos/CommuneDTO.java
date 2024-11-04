package com.mindhub.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class CommuneDTO {
    private long id;
    private int idRegion;
    private String communes;
}
