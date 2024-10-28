package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.PurchasingDetailDTO;
import com.mindhub.retailhome.mappers.AccountMapper;
import com.mindhub.retailhome.mappers.PurchasingDetailMapper;
import com.mindhub.retailhome.models.PurchasingDetail;
import com.mindhub.retailhome.models.PurchasingHeader;
import com.mindhub.retailhome.repositories.PurchasingDetailRepository;
import com.mindhub.retailhome.service.PurchasingDetailService;
import com.mindhub.retailhome.utils.Constants;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchasingDetailServiceImpl implements PurchasingDetailService {
    private Map<String, Object> response;
    private HttpStatus http;
    private PurchasingDetailDTO purchasingDetailDtoNew;
    private PurchasingDetail purchasingDetailNew;
    private PurchasingDetailMapper purchasingDetailMapper;
    private final PurchasingDetailMapper mapper = Mappers.getMapper(PurchasingDetailMapper.class);

    PurchasingDetailServiceImpl(PurchasingDetailRepository purchasingDetailRepository){
        this.purchasingDetailRepository = purchasingDetailRepository;
    }
    private PurchasingDetailRepository purchasingDetailRepository;

    @Override
    public ResponseEntity<?> finAll() {
        response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        List<PurchasingDetailDTO> listDto = new ArrayList<>();

        try {
            this.purchasingDetailRepository.findAll().forEach(purchasingDetail ->
                    listDto.add(this.purchasingDetailMapper.toPurchasingDetailDto(purchasingDetail))
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
    public ResponseEntity<?> findById(Long id) {
        this.response = new HashMap<>();
        this.purchasingDetailDtoNew = new PurchasingDetailDTO();
        this.http = HttpStatus.NOT_FOUND;
        try {
            if (id != null) {
                this.purchasingDetailDtoNew = mapper.toPurchasingDetailDto(purchasingDetailRepository.findById(id).orElse(null));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.PURCHASING_DETAIL.PURCHASING_DETAILS,  this.purchasingDetailDtoNew);
                this.http = HttpStatus.ACCEPTED;
            }else{
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, this.http);
    }

    @Override
    public ResponseEntity<?> save(PurchasingDetailDTO purchasingDetailDTO) {
       this.response = new HashMap<>();
       this.purchasingDetailDtoNew = null;
       this.purchasingDetailNew = null;

       try {

           this.purchasingDetailNew = this.purchasingDetailRepository.save(this.purchasingDetailMapper.toPurchasingDetail(purchasingDetailDTO));
           this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
           this.response.put(Constants.USER.USER, purchasingDetailNew);
           this.http = HttpStatus.CREATED;
       } catch (Exception e) {
           this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
           this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
           this.http = HttpStatus.BAD_REQUEST;
       }

       return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public ResponseEntity<?> update(PurchasingDetailDTO purchasingDetailDTO) {
        this.response = new HashMap<>();
        this.purchasingDetailDtoNew = null;
        this.purchasingDetailNew = null;
        this.http = HttpStatus.NOT_FOUND;

        try {
            this.purchasingDetailNew = this.purchasingDetailRepository.findById(purchasingDetailDTO.getIdPurchasing()).orElse(null);
            this.purchasingDetailDtoNew = mapper.toPurchasingDetailDto(this.purchasingDetailNew);
            if(purchasingDetailDtoNew == null){
                this.response.put(Constants.GEMERAL.ERROR,Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else{
                purchasingDetailDtoNew.setProduct(purchasingDetailDTO.getProduct());
                purchasingDetailDtoNew.setAmount(purchasingDetailDTO.getAmount());
                purchasingDetailDtoNew.setWorth(purchasingDetailDTO.getWorth());
                purchasingDetailDtoNew.setTax(purchasingDetailDTO.getTax());

                purchasingDetailNew = this.purchasingDetailRepository.save(this.mapper.toPurchasingDetail(purchasingDetailDtoNew));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, purchasingDetailNew);
                this.http = HttpStatus.ACCEPTED;
            }

        } catch (Exception e) {
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);
    }
}
