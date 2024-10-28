package com.retailhome.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;


@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Component
public class CityDTO {
    private long id;
    private String name;
    private List<Integer> region;
    private List<Integer> communes;

}
