package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.CreditsHeaderDTO;
import com.mindhub.homebanking.dtos.PurchasingDetailDTO;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.CreditsHeaderRepository;
import com.mindhub.homebanking.service.CreditsHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreditsHeaderServiceImpl implements CreditsHeaderService {
    private Map<String, Object> response;
    private HttpStatus http;
    private CreditsHeaderDTO creditsHeaderDTONew;

    @Autowired
    private CreditsHeaderRepository creditsHeaderRepository;

    @Override
    public Set<CreditsHeaderDTO> finAll() {
        return this.creditsHeaderRepository.findAll().stream().map(CreditsHeaderDTO::new).collect(Collectors.toSet());
    }

    @Override
    public CreditsHeaderDTO findById(Long id) {
        return this.creditsHeaderRepository.findById(id).map(CreditsHeaderDTO::new).orElse(null);
    }

    @Override
    public ResponseEntity<?> save(CreditsHeaderDTO creditsHeaderDTO) {
        this.response = new HashMap<>();
        creditsHeaderDTONew = creditsHeaderDTO;
        try {
//            this.accountRepository.save(accountDTO);
            this.creditsHeaderDTONew.setEnabled(true);
            this.response.put("mensaje genenetal", "OPERATION_OK");
            this.response.put("cuenta creada", creditsHeaderDTONew);
            this.http = HttpStatus.CREATED;

        }catch (Exception e){
            this.response.put("mensaje genenetal", "OPERATION_NOT_OK");
            this.response.put("mensaje ERROR", e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public boolean delete(CreditsHeaderDTO creditsHeaderDTO) {
        boolean operation = false;

        CreditsHeaderDTO creditsHeaderDTONew = findById(creditsHeaderDTO.getId());

        try {
            creditsHeaderDTONew.setEnabled(false);

            update(creditsHeaderDTONew);

            operation = true;

        }catch (Exception e){
            operation = false;
        }

        return operation;
    }

    @Override
    public ResponseEntity<?> update(CreditsHeaderDTO creditsHeaderDTO) {
        this.response = new HashMap<>();
        this.creditsHeaderDTONew = null;

        try {
            creditsHeaderDTONew = this.creditsHeaderRepository.findById(creditsHeaderDTO.getId()).map(CreditsHeaderDTO::new).orElse(null);

            this.response.put("Mensaje General","Operacion OK");
            this.response.put("Datos actualizados",creditsHeaderDTONew);
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
