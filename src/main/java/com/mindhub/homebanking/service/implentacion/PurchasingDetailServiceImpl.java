package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.PurchasingDetailDTO;
import com.mindhub.homebanking.repositories.PurchasingDetailRepository;
import com.mindhub.homebanking.service.PurchasingDetailService;
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
        purchasingDetailDTONew = purchasingDetailDTO;
        try {
//            this.accountRepository.save(accountDTO);
            this.response.put("mensaje genenetal", "OPERATION_OK");
            this.response.put("cuenta creada", purchasingDetailDTONew);
            this.http = HttpStatus.CREATED;

        }catch (Exception e){
            this.response.put("mensaje genenetal", "OPERATION_NOT_OK");
            this.response.put("mensaje ERROR", e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public ResponseEntity<?> update(PurchasingDetailDTO purchasingDetailDTO) {
        this.response = new HashMap<>();
        this.purchasingDetailDTONew = null;

        try {
            purchasingDetailDTONew = this.purchasingdetailrepository.findById(purchasingDetailDTO.getId()).map(PurchasingDetailDTO::new).orElse(null);

            this.response.put("Mensaje General","Operacion OK");
            this.response.put("Datos actualizados",purchasingDetailDTONew);
            http = HttpStatus.ACCEPTED;

        }catch (Exception e){
//            response new ResponseEntity<>(accountDTONew, HttpStatus.BAD_REQUEST);
            this.response.put("Mensaje General","Operacion NOOK");
            this.response.put("Mensaje error",e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }

        //que se puede enviar en el response entity
        return new ResponseEntity<>(this.response,this.http);
    }
}
