package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.CreditDetailDTO;
import com.mindhub.retailhome.mappers.CreditDetailMapper;
import com.mindhub.retailhome.models.CreditDetail;
import com.mindhub.retailhome.repositories.CreditDetailRepository;
import com.mindhub.retailhome.service.CreditDetailService;
import com.mindhub.retailhome.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreditDetailServiceImpl implements CreditDetailService {

    private Map<String, Object> response;
    private HttpStatus http;
    private CreditDetailDTO creditDetailDtoNew;
    private CreditDetailDTO creditDetailDtoOld;
    private CreditDetail creditDetailNew;
    private CreditDetailMapper creditDetailMapper;

    @Autowired
    private CreditDetailRepository creditDetailRepository;

    @Override
    public Set<CreditDetailDTO> finAll() {
        return this.creditDetailRepository.findAll().stream().map(CreditDetailDTO::new).collect(Collectors.toSet());
    }

    @Override
    public CreditDetailDTO findById(Long id) {
        return this.creditDetailRepository.findById(id).map(CreditDetailDTO::new).orElse(null);
    }

    @Override
    public ResponseEntity<?> save(CreditDetailDTO creditDetailDTO) {
        this.response = new HashMap<>();
        creditDetailDtoNew = creditDetailDTO;
        this.creditDetailNew = null;

        try {

            this.creditDetailNew = this.creditDetailRepository.save(this.creditDetailMapper.creditDetailDtoToCreditDetail(creditDetailDTO));
            this.creditDetailDtoNew = creditDetailMapper.creditDetailToCreditDetailDto(creditDetailRepository.save(creditDetailNew));

            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, creditDetailDtoNew);
            this.http = HttpStatus.CREATED;

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response, this.http);
    }
    @Override
    public ResponseEntity<?> update(CreditDetailDTO creditDetailDTO) {
        this.response = new HashMap<>();
        this.creditDetailDtoNew = null;
        this.creditDetailDtoOld = null;
        this.creditDetailNew = null;

        try {
            creditDetailDTO = findById(creditDetailDTO.getIdCredit());
            if (creditDetailDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else {

                creditDetailDtoOld.setDateExpiration(creditDetailDTO.getDateExpiration());
                creditDetailDtoOld.setQuotaStatus(creditDetailDTO.getQuotaStatus());
                creditDetailDtoOld.setPayDay(creditDetailDTO.getPayDay());

               creditDetailNew = this.creditDetailRepository.save(this.creditDetailMapper.creditDetailDtoToCreditDetail(creditDetailDtoOld));
                this.creditDetailDtoNew = creditDetailMapper.creditDetailToCreditDetailDto(creditDetailRepository.save(creditDetailNew));

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, creditDetailDtoNew);
                http = HttpStatus.ACCEPTED;
            }
        }catch (Exception e){
//            response new ResponseEntity<>(accountDTONew, HttpStatus.BAD_REQUEST);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response,this.http);
    }
}
