package com.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Component
public class CommuneDTO {
    private long id;

    private int idRegion;
    private String communes;

}
