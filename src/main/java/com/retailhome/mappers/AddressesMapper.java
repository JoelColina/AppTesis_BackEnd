package com.retailhome.mappers;


import com.retailhome.dtos.AddressesDTO;
import com.retailhome.models.Addresses;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface AddressesMapper {

    Addresses toAddresses (AddressesDTO addressesDTO);

    AddressesDTO toAddressesDto (Addresses addresses);

    default List<AddressesDTO> toDTOList(List<Addresses> addressList){
        if (addressList == null){
            return  new ArrayList<>();
        }
        return addressList.stream().map(this::toAddressesDto).collect(Collectors.toList());
    }

    default List<Addresses> toEntityList(List<AddressesDTO> addressDtoList){
        if (addressDtoList == null){
            return  new ArrayList<>();
        }
        return addressDtoList.stream().map(this:: toAddresses).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Addresses updateAccountFromAccountDto(AddressesDTO accountDto, @MappingTarget Addresses account);
}


