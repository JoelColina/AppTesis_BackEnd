package com.mindhub.homebanking.dtos;

public class CommuneDTO {
    private long id;

    private int idRegion;
    private String comuna;

    public CommuneDTO(Commune commune) {
        this.id = commune.getId();
        this.idRegion = commune.getIdRegion();
        this.comuna = commune.getComuna();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
}
