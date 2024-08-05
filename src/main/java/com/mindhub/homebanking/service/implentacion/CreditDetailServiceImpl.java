package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.CreditDetailDTO;
import com.mindhub.homebanking.dtos.PurchasingDetailDTO;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.CreditDetailRepository;
import com.mindhub.homebanking.service.CreditDetailService;
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
        creditDetailDTONew = creditDetailDTO;
        try {
            this.response.put("mensaje genenetal", "OPERATION_OK");
            this.response.put("cuenta creada", creditDetailDTONew);
            this.http = HttpStatus.CREATED;

        }catch (Exception e){
            this.response.put("mensaje genenetal", "OPERATION_NOT_OK");
            this.response.put("mensaje ERROR", e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response, this.http);
    }
    @Override
    public ResponseEntity<?> update(CreditDetailDTO creditDetailDTO) {
        this.response = new HashMap<>();
        this.creditDetailDTONew = null;

        try {
            creditDetailDTONew = this.creditDetailRepository.findById(creditDetailDTO.getId()).map(CreditDetailDTO::new).orElse(null);

            this.response.put("Mensaje General","Operacion OK");
            this.response.put("Datos actualizados",creditDetailDTONew);
            http = HttpStatus.ACCEPTED;

        }catch (Exception e){
//            response new ResponseEntity<>(accountDTONew, HttpStatus.BAD_REQUEST);
            this.response.put("Mensaje General","Operacion NOOK");
            this.response.put("Mensaje error",e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response,this.http);
    }
}
