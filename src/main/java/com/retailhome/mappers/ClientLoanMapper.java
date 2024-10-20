package com.retailhome.mappers;

import com.retailhome.dtos.ClientLoanDTO;
import com.retailhome.models.ClientLoan;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ClientLoanMapper {

    ClientLoan toClientLoan (ClientLoanDTO clientLoanDTO);

    ClientLoanDTO toClientLoanDto (ClientLoan clientLoan);

    default List<ClientLoanDTO> toDtoList(List<ClientLoan> clientLoansList){
        if (clientLoansList == null){
            return  new ArrayList<>();
        }
        return clientLoansList.stream().map(this::toClientLoanDto).collect(Collectors.toList());
    }

    default List<ClientLoan> toEntityList(List<ClientLoanDTO> clientLoanDTOList){
        if (clientLoanDTOList == null){
            return  new ArrayList<>();
        }
        return clientLoanDTOList.stream().map(this::toClientLoan).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ClientLoan  updateClientLoanFromClientLoanDto(ClientLoanDTO clientLoanDTO, @MappingTarget ClientLoan authority);
}
