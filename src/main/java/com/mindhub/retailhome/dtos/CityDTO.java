package com.mindhub.retailhome.dtos;

import java.util.List;

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

    public List<Integer> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Integer> communes) {
        this.communes = communes;
    }
}
