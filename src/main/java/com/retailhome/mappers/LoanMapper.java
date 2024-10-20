package com.retailhome.mappers;

import com.retailhome.dtos.LoanDTO;
import com.retailhome.models.Loan;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface LoanMapper {
    Loan toLoan (LoanDTO loanDTO);

    LoanDTO toLoanDto (Loan loan);

    default List<Loan> toEntityList(List<LoanDTO> loanDTOList){
        if (loanDTOList == null){
            return  new ArrayList<>();
        }
        return loanDTOList.stream().map(this::toLoan).collect(Collectors.toList());
    }

    default List<LoanDTO> toDtoList(List<Loan> loanList){
        if (loanList == null){
            return  new ArrayList<>();
        }
        return loanList.stream().map(this::toLoanDto).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Loan updateLoanFromLoanDto(LoanDTO loanDTO, @MappingTarget Loan loan);

}
