package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.AddressesDTO;
import com.mindhub.retailhome.models.Addresses;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface AddressesMapper {

    Addresses addressesDtoToAddresses (AddressesDTO addressesDTO);

    AddressesDTO addressesToaddressesDto (Addresses addresses);

}
