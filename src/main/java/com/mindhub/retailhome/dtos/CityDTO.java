package com.mindhub.retailhome.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CityDTO {
    private long id;
    private String name;
    private List<Integer> region;
    private List<Integer> communes;

    public CityDTO(String name, List<Integer> region, List<Integer> communes) {
        this.name = name;
        this.region = region;
        this.communes = communes;
    }

}
