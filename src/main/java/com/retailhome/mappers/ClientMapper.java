package com.retailhome.mappers;

import com.retailhome.dtos.ClientDTO;
import com.retailhome.models.Client;
import org.mapstruct.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper
public interface ClientMapper {

    Client toClient(ClientDTO clientDto);

    ClientDTO toClientDTO(Client client);

    default List<ClientDTO> toDtoList(List<Client> ClientList){
        if (ClientList == null){
            return  new ArrayList<>();
        }
        return ClientList.stream().map(this::toClientDTO).collect(Collectors.toList());
    }

    default List<Client> toEntityList(List<ClientDTO> ClientDTOList){
        if (ClientDTOList == null){
            return  new ArrayList<>();
        }
        return ClientDTOList.stream().map(this::toClient).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Client updateClientFromClientDTO(ClientDTO ClientDTO, @MappingTarget Client Client);
}
