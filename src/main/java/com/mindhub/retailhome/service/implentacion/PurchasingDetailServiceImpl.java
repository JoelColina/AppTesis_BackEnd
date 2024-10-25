package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.PurchasingDetailDTO;
import com.mindhub.retailhome.mappers.PurchasingDetailMapper;
import com.mindhub.retailhome.models.PurchasingDetail;
import com.mindhub.retailhome.models.PurchasingHeader;
import com.mindhub.retailhome.repositories.PurchasingDetailRepository;
import com.mindhub.retailhome.service.PurchasingDetailService;
import com.mindhub.retailhome.utils.Constants;
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
    private PurchasingDetailRepository purchasingdetailrepository;

    PurchasingDetailServiceImpl(PurchasingDetailRepository purchasingdetailrepository
    ) {
        this.purchasingdetailrepository = purchasingdetailrepository;
    }

    @Override
    public ResponseEntity<?> finAll() {
        response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        List<PurchasingDetailDTO> listDto = new ArrayList<>();

        try {
            this.purchasingdetailrepository.findAll().forEach(PurchasingDetail ->
                    listDto.add(this.purchasingDetailMapper.toPurchasingDetailDto(PurchasingDetail))
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
    public PurchasingDetailDTO findById(Long id) {
        this.response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        if (id == null) {
            return this.purchasingdetailrepository.findById(id).map(PurchasingDetailDTO::new).orElse(null);
        }
        return new PurchasingDetailDTO();
    }

    @Override
    public ResponseEntity<?> save(PurchasingDetailDTO purchasingDetailDTO) {
       this.response = new HashMap<>();
       this.purchasingDetailDtoNew = null;
       this.purchasingDetailNew = null;

       try {

           this.purchasingDetailNew = this.purchasingdetailrepository.save(this.purchasingDetailMapper.toPurchasingDetail(purchasingDetailDTO));
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
        try {
            purchasingDetailDTO = findById(purchasingDetailDTO.getId());
            if(purchasingDetailDTO == null){
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else{
                purchasingDetailDtoNew.setAmount(purchasingDetailDTO.getAmount());
                purchasingDetailDtoNew.setProduct(purchasingDetailDTO.getProduct());
                purchasingDetailDtoNew.setTax(purchasingDetailDTO.getTax());
                purchasingDetailDtoNew.setWorth(purchasingDetailDTO.getWorth());

                this.purchasingDetailNew = this.purchasingdetailrepository.save(this.purchasingDetailMapper.toPurchasingDetail(purchasingDetailDtoNew));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, purchasingDetailNew);
                this.http = HttpStatus.CREATED;
            }
        } catch (Exception e) {
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);
    }
}
