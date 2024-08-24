package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.AddressType;
import com.mindhub.retailhome.models.Addresses;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressesDTO {

    private String address;
    private Number number;
    private String city;
    private String commune;
    private Number postalCode;
    private AddressType type;
    private boolean enabled;
    private Long idClient;

    public AddressesDTO(Addresses addresses){
        this.address = addresses.getAddress();
        this.number = addresses.getNumber();
        this.city = addresses.getCity();
        this.commune = addresses.getCommune();
        this.postalCode = addresses.getPostalCode();
        this.type = addresses.getType();
        this.enabled = addresses.isEnabled();
        this.idClient = addresses.getIdClient();
    }

    public AddressesDTO() {
    }

}
