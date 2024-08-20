package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.ClientDTO;
import com.mindhub.retailhome.models.Client;
import org.mapstruct.Mapper;


@Mapper
public interface ClientMapper {

    Client clientDtoToClient(ClientDTO clientDto);

    ClientDTO clientToClientDto(Client client);
}
