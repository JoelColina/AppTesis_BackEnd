package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.PurchasingHeaderDTO;
import com.mindhub.homebanking.repositories.PurchasingHeaderRepository;
import com.mindhub.homebanking.service.PurchasingHeaderService;
import com.mindhub.homebanking.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PurchasingHeaderServiceImpl implements PurchasingHeaderService {

    private Map<String, Object> response;
    private HttpStatus http;
    private PurchasingHeaderDTO purchasingHeaderDTONew;

    @Autowired
    private PurchasingHeaderRepository purchasingHeaderRepository;

    @Override
    public Set<PurchasingHeaderDTO> finAll() {
        return this.purchasingHeaderRepository.findAll().stream().map(PurchasingHeaderDTO::new).collect(Collectors.toSet());
    }

    @Override
    public PurchasingHeaderDTO findById(Long id) {
        return this.purchasingHeaderRepository.findById(id).map(PurchasingHeaderDTO::new).orElse(null);
    }

    @Override
    public ResponseEntity<?> save(PurchasingHeaderDTO purchasingHeaderDTO) {
        this.response = new HashMap<>();
        purchasingHeaderDTONew = purchasingHeaderDTO;
        try {
//            this.accountRepository.save(accountDTO);
            this.purchasingHeaderDTONew.setEnabled(true);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, purchasingHeaderDTONew);
            this.http = HttpStatus.CREATED;

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public boolean delete(PurchasingHeaderDTO purchasingHeaderDTO) {
        boolean operation = false;

        PurchasingHeaderDTO purchasingHeaderDTONew = findById(purchasingHeaderDTO.getId());

        try {
            purchasingHeaderDTONew.setEnabled(false);
            update(purchasingHeaderDTONew);
            operation = true;

        }catch (Exception e){
            operation = false;
        }

        return operation;
    }

    @Override
    public ResponseEntity<?> update(PurchasingHeaderDTO purchasingHeaderDTO) {
        this.response = new HashMap<>();
        this.purchasingHeaderDTONew = null;

        try {
            purchasingHeaderDTONew = this.purchasingHeaderRepository.findById(purchasingHeaderDTO.getId()).map(PurchasingHeaderDTO::new).orElse(null);

            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, purchasingHeaderDTONew);
            http = HttpStatus.ACCEPTED;

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
