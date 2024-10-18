package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.utils.enums.AddressType;
import com.mindhub.retailhome.models.Addresses;
import lombok.*;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@Component
public class AddressesDTO {

    private String address;
    private Number number;
    private String city;
    private String commune;
    private Number postalCode;
    private AddressType type;
    private boolean enabled;
    private Long idClient;



}
