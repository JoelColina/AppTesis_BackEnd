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
    private PurchasingDetailDTO purchasingDetailDTONew;
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

        try {
            this.purchasingDetailNew = this.purchasingdetailrepository.save(this.purchasingDetailMapper.purchasingDetailDtoToPurchasingDetail(purchasingDetailDTO));

//            this.accountRepository.save(accountDTO);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, purchasingDetailNew);
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
        this.purchasingDetailDTONew = null;
        this.purchasingDetailNew = null;

        try {
            purchasingDetailDTO = findById(purchasingDetailDTO.getIdPurchasing());
            if (purchasingDetailDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;

            }else {
                purchasingDetailDTONew.setProduct(purchasingDetailDTO.getProduct());
                purchasingDetailDTONew.setAmount(purchasingDetailDTO.getAmount());
                purchasingDetailDTONew.setWorth(purchasingDetailDTO.getWorth());
                purchasingDetailDTONew.setTax(purchasingDetailDTO.getTax());

                purchasingDetailNew = this.purchasingdetailrepository.save(this.purchasingDetailMapper.purchasingDetailDtoToPurchasingDetail(purchasingDetailDTONew));

//                purchasingDetailDTONew = this.purchasingdetailrepository.findById(purchasingDetailDTO.getId()).map(PurchasingDetailDTO::new).orElse(null);

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, purchasingDetailNew);
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
