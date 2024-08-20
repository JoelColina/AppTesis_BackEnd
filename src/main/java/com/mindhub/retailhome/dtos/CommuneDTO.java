package com.mindhub.retailhome.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommuneDTO {
    private long id;

    private int idRegion;
    private String communes;

    public CommuneDTO(long id, int idRegion, String communes) {
        this.id = id;
        this.idRegion = idRegion;
        this.communes = communes;
    }

}
