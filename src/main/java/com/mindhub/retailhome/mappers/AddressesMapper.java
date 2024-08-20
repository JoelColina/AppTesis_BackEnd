package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.AddressesDTO;
import com.mindhub.retailhome.models.Addresses;
import org.mapstruct.Mapper;

@Mapper
public interface AddressesMapper {

    Addresses addressesDtoToAddresses (AddressesDTO addressesDTO);

    AddressesDTO AddressesToaddressesDto (Addresses addresses);

}
