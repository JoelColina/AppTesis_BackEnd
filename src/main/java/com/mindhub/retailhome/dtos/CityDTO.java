package com.mindhub.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CityDTO {
    private long id;
    private String name;
    private List<Integer> region;
    private List<Integer> communes;

}
