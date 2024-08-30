package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.PurchasingDetailDTO;
import com.mindhub.retailhome.mappers.PurchasingDetailMapper;
import com.mindhub.retailhome.models.PurchasingDetail;
import com.mindhub.retailhome.repositories.PurchasingDetailRepository;
import com.mindhub.retailhome.service.PurchasingDetailService;
import com.mindhub.retailhome.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PurchasingDetailServiceImpl implements PurchasingDetailService {
    private Map<String, Object> response;
    private HttpStatus http;
    private PurchasingDetailDTO purchasingDetailDtoNew;
    private PurchasingDetailDTO purchasingDetailDtoOld;
    private PurchasingDetail purchasingDetailNew;
    private PurchasingDetailMapper purchasingDetailMapper;

    @Autowired
    private PurchasingDetailRepository purchasingdetailrepository;

    @Override
    public Set<PurchasingDetailDTO> finAll() {
        return this.purchasingdetailrepository.findAll().stream().map(PurchasingDetailDTO::new).collect(Collectors.toSet());
    }

    @Override
    public PurchasingDetailDTO findById(Long id) {
        return this.purchasingdetailrepository.findById(id).map(PurchasingDetailDTO::new).orElse(null);
    }

    @Override
    public ResponseEntity<?> save(PurchasingDetailDTO purchasingDetailDTO) {
        this.response = new HashMap<>();
        purchasingDetailNew = null;
        purchasingDetailDtoNew = null;

        try {
            this.purchasingDetailNew = this.purchasingdetailrepository.save(this.purchasingDetailMapper.purchasingDetailDtoToPurchasingDetail(purchasingDetailDTO));
            this.purchasingDetailDtoNew = purchasingDetailMapper.purchasingDetailToPurchasingDetailDto(purchasingdetailrepository.save(purchasingDetailNew));

            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, purchasingDetailDtoNew);
            this.http = HttpStatus.CREATED;

        }catch (Exception e){
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
        this.purchasingDetailDtoOld = null;
        this.purchasingDetailNew = null;

        try {
            purchasingDetailDTO = findById(purchasingDetailDTO.getIdPurchasing());
            if (purchasingDetailDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;

            }else {
                purchasingDetailDtoOld.setProduct(purchasingDetailDTO.getProduct());
                purchasingDetailDtoOld.setAmount(purchasingDetailDTO.getAmount());
                purchasingDetailDtoOld.setWorth(purchasingDetailDTO.getWorth());
                purchasingDetailDtoOld.setTax(purchasingDetailDTO.getTax());

                purchasingDetailNew = this.purchasingdetailrepository.save(this.purchasingDetailMapper.purchasingDetailDtoToPurchasingDetail(purchasingDetailDtoOld));
                this.purchasingDetailDtoNew = purchasingDetailMapper.purchasingDetailToPurchasingDetailDto(purchasingdetailrepository.save(purchasingDetailNew));

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, purchasingDetailDtoNew);
                http = HttpStatus.ACCEPTED;
            }
        }catch (Exception e){
//            response new ResponseEntity<>(accountDTONew, HttpStatus.BAD_REQUEST);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }

        //que se puede enviar en el response entity
        return new ResponseEntity<>(this.response,this.http);
    }
}
