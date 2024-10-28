package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.AccountDTO;
import com.mindhub.retailhome.dtos.ClientDTO;
import com.mindhub.retailhome.models.Account;
import com.mindhub.retailhome.models.Client;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper
public interface ClientMapper {

    Client toClient(ClientDTO clientDto);

    ClientDTO toClientDto(Client client);

    default List<ClientDTO> toDtoList(List<Client> ClientList){
        if (ClientList == null){
            return  new ArrayList<>();
        }
        return ClientList.stream().map(this::toClientDto).collect(Collectors.toList());
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
