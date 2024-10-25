package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.LoanDTO;
import com.mindhub.retailhome.dtos.PurchasingDetailDTO;
import com.mindhub.retailhome.models.Loan;
import com.mindhub.retailhome.models.PurchasingDetail;
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

    default List<LoanDTO> toDoList (List<Loan> LoanList){
        if (LoanList == null) {
            return new ArrayList<>();
        }
        return LoanList.stream().map(this::toLoanDto).collect(Collectors.toList());
    }

    default List<Loan> toEntityList (List<LoanDTO> loanDTOList){
        if (loanDTOList == null) {
            return new ArrayList<>();
        }
        return loanDTOList.stream().map(this::toLoan).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    PurchasingDetail updateLoanFromLoanDto(LoanDTO loanDTO, @MappingTarget Loan loan);


}
