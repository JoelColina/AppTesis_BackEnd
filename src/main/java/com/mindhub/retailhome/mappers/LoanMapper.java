package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.LoanDTO;
import com.mindhub.retailhome.models.Loan;

public interface LoanMapper {
    Loan loanDtoToLoan (LoanDTO loanDTO);

    LoanDTO loanToLoanDto (Loan loan);
}
