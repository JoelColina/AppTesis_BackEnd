package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.LoanApplicationDTO;
import com.mindhub.retailhome.dtos.LoanDTO;
import com.mindhub.retailhome.dtos.PurchasingDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {
    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long id);

    ResponseEntity<Object> newRegister(LoanApplicationDTO loanApplicationDTO,
                                       Authentication authentication);
}
