package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.CreditDetailDTO;
import com.mindhub.retailhome.mappers.CreditDetailMapper;
import com.mindhub.retailhome.models.CreditDetail;
import com.mindhub.retailhome.repositories.CreditDetailRepository;
import com.mindhub.retailhome.service.CreditDetailService;
import com.mindhub.retailhome.utils.Constants;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CreditDetailServiceImpl implements CreditDetailService {

    private Map<String, Object> response;
    private HttpStatus http;
    private CreditDetailDTO creditDetailDtoNew;
    private CreditDetail creditDetailNew;
    private CreditDetailMapper creditDetailMapper;
    private final CreditDetailMapper mapper = Mappers.getMapper(CreditDetailMapper.class);

    CreditDetailServiceImpl(CreditDetailRepository creditDetailRepository) {
        this.creditDetailRepository = creditDetailRepository;
    }
    private final CreditDetailRepository creditDetailRepository;

    @Override
    public ResponseEntity<?> finAll() {
        response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        List<CreditDetailDTO> listDto = new ArrayList<>();

        try {
            this.creditDetailRepository.findAll().forEach(CreditDetail ->
                    listDto.add(this.creditDetailMapper.toCreditDetailDto(CreditDetail))
            );
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.PURCHASING_DETAIL.PURCHASING_DETAILS, listDto);
            this.http = HttpStatus.ACCEPTED;

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(listDto, this.http);
    }

    @Override
    public CreditDetailDTO findById(Long id) {
        this.response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        if (id == null) {
            return this.creditDetailRepository.findById(id).map(CreditDetailDTO::new).orElse(null);
        }
        return new CreditDetailDTO();
    }

    @Override
    public ResponseEntity<?> save(CreditDetailDTO creditDetailDTO) {
        this.response = new HashMap<>();
        this.creditDetailDtoNew = null;
        this.creditDetailNew = null;

        try {

            this.creditDetailNew = this.creditDetailRepository.save(this.creditDetailMapper.toCreditDetail(creditDetailDTO));
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, creditDetailNew);
            this.http = HttpStatus.CREATED;
        } catch (Exception e) {
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
        this.creditDetailNew = null;

        try {
            creditDetailDTO = findById(creditDetailDTO.getIdCredit());
            if (creditDetailDTO == null){
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else {

                creditDetailDtoNew.setDateExpiration(creditDetailDTO.getDateExpiration());
                creditDetailDtoNew.setQuotaStatus(creditDetailDTO.getQuotaStatus());
                creditDetailDtoNew.setPayDay(creditDetailDTO.getPayDay());

                creditDetailNew = this.creditDetailRepository.save(this.creditDetailMapper.toCreditDetail(creditDetailDtoNew));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, creditDetailNew);
                http = HttpStatus.ACCEPTED;
            }
        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response,this.http);
    }
}
