package com.retailhome.service.implentacion;

import com.retailhome.dtos.CreditDetailDTO;
import com.retailhome.mappers.CreditDetailMapper;
import com.retailhome.models.CreditDetail;
import com.retailhome.repositories.CreditDetailRepository;
import com.retailhome.service.CreditDetailService;
import com.retailhome.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreditDetailServiceImpl implements CreditDetailService {

    private Map<String, Object> response;
    private HttpStatus http;
    private CreditDetailDTO creditDetailDTONew;
    private CreditDetail creditDetailNew;
    private CreditDetailMapper creditDetailMapper;
    private final CreditDetailRepository creditDetailRepository;

    public CreditDetailServiceImpl(CreditDetailRepository creditDetailRepository) {
        this.creditDetailRepository = creditDetailRepository;
    }

    @Override
    public Set<CreditDetailDTO> finAllDetails() {
        return Set.of();
    }

    @Override
    public CreditDetailDTO findByIdDetail(Long id) {
        return null;
    }

    @Override
    public CreditDetailDTO saveDetail(CreditDetailDTO creditDetailDTO) {
        return null;
    }

    @Override
    public CreditDetailDTO updateD(CreditDetailDTO creditDetailDTO) {
        return null;
    }

    @Override
    public boolean deleteDetail(CreditDetailDTO creditDetailDTO) {
        return false;
    }
}
