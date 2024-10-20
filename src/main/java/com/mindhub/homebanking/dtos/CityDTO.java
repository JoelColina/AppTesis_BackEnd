package com.mindhub.homebanking.dtos;

import java.util.List;

public class CityDTO {
    private long id;
    private String name;
    private List<Integer> region;
    private List<Integer> communes;

    public CityDTO(City city) {

        this.id       = city.getId();
        this.name     = city.getName();
        this.region   = city.getRegion();
        this.communes = city.communes();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getRegion() {
        return region;
    }

    public void setRegion(List<Integer> region) {
        this.region = region;
    }
}
