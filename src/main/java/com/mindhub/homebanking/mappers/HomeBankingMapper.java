package com.mindhub.homebanking.mappers;

import com.mindhub.homebanking.dtos.AddressesDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Addresses;
import com.mindhub.homebanking.models.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface HomeBankingMapper {

    HomeBankingMapper INSTANCE = Mappers.getMapper(HomeBankingMapper.class);

    @Mapping(source = "client.email", target = "email", defaultValue = "email")
    ClientDTO clientToClientDto(Client client);

    @Mapping(source = "clientDto.email", target = "email", defaultValue = "email")
    Client clientDtoToClient(ClientDTO clientDto);

    @Mapping(source = "addresses.id", target = "id", defaultValue = "id")
    AddressesDTO addresseToAddressesDto(Addresses addresses);

    @Mapping(source = "addressesDto.id", target = "id", defaultValue = "id")
    Addresses addresseDtoToAddresses(AddressesDTO addressesDto);

}
