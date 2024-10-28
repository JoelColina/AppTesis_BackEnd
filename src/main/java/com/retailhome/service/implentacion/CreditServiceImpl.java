package com.retailhome.service.implentacion;


import com.retailhome.dtos.CreditDetailDTO;
import com.retailhome.dtos.CreditDto;
import com.retailhome.dtos.CreditsHeaderDTO;
import com.retailhome.mappers.CreditDetailMapper;
import com.retailhome.mappers.CreditsHeaderMapper;
import com.retailhome.repositories.CreditDetailRepository;
import com.retailhome.repositories.CreditsHeaderRepository;
import com.retailhome.service.CreditDetailService;
import com.retailhome.service.CreditsHeaderService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CreditServiceImpl implements CreditDetailService, CreditsHeaderService {

     public CreditDetailRepository creditDetailRepository;
     public CreditsHeaderRepository creditsHeaderRepository;
     private CreditDetailDTO creditDetailDTO;
     private CreditsHeaderDTO creditsHeaderDTO;
     private CreditDto creditDto;
     private final CreditsHeaderMapper mapperHeater = Mappers.getMapper(CreditsHeaderMapper.class);
     private final CreditDetailMapper    mapperdetail = Mappers.getMapper(CreditDetailMapper.class);

    public CreditServiceImpl(CreditDetailRepository creditDetailRepository,
                              CreditsHeaderRepository creditsHeaderRepository) {
        this.creditDetailRepository = creditDetailRepository;
        this.creditsHeaderRepository = creditsHeaderRepository;
    }

    @Override
    public ResponseEntity<?> finAll() {
      this.creditsHeaderDTO = new CreditsHeaderDTO();
      this.creditDetailDTO = new CreditDetailDTO();
       List<CreditDto> listCredit = new ArrayList<>();
       List<CreditsHeaderDTO> listCreditsHeader = new ArrayList<>();
       List<CreditDetailDTO> listCreditDetail = new ArrayList<>();

      try{
         listCreditsHeader = this.mapperHeater.toDTOList(  this.creditsHeaderRepository.findAll());
         for (CreditsHeaderDTO creditsHeaderDTO : listCreditsHeader) {














         }


      } catch(Exception e){

      }
















    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> save(CreditsHeaderDTO creditsHeaderDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(CreditsHeaderDTO creditsHeaderDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(CreditsHeaderDTO creditsHeaderDTO) {
        return null;
    }

    @Override
    public   Set<CreditDetailDTO> finAllDetails() {
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