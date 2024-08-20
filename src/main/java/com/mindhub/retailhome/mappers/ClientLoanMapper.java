package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.ClientLoanDTO;
import com.mindhub.retailhome.models.ClientLoan;
import org.mapstruct.Mapper;

@Mapper
public interface ClientLoanMapper {

    ClientLoan clientLoanDtoToClientLoan (ClientLoanDTO clientLoanDTO);

    ClientLoanDTO clientLoanToClientLoanDto (ClientLoan clientLoan);

}
