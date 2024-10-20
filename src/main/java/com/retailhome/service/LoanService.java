package com.retailhome.service;

import com.retailhome.dtos.LoanApplicationDTO;
import com.retailhome.dtos.LoanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {
    List<LoanDTO> findAll();

    LoanDTO findById(long id);

    ResponseEntity<Object> newRegister(LoanApplicationDTO loanApplicationDTO,
                                       Authentication authentication);
}
