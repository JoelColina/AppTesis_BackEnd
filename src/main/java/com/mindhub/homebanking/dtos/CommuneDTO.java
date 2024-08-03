package com.mindhub.homebanking.dtos;

public class CommuneDTO {
    private long id;

    private int idRegion;
    private String communes;

    public CommuneDTO(long id, int idRegion, String communes) {
        this.id = id;
        this.idRegion = idRegion;
        this.communes = communes;
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

    public String getCommunes() {
        return communes;
    }

    public void setCommunes(String communes) {
        this.communes = communes;
    }
}
