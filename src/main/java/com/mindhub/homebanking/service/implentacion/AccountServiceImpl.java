package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private Map<String, Object> response;
    private HttpStatus http;


    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Set<AccountDTO> findAll() {
        return this.accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toSet());
    }

    @Override
    public AccountDTO findById(Long id) {
        return this.accountRepository.findById(id).map(AccountDTO::new).orElse(null);
    }

    @Override
    public ResponseEntity<?> update(AccountDTO accountDTO) {

        this.response = new HashMap<>();

        AccountDTO accountDTONew = null;

        try {
            accountDTONew = this.accountRepository.findById(accountDTO.getId()).map(AccountDTO::new).orElse(null);

//            return new ResponseEntity<>(accountDTONew, HttpStatus.ACCEPTED);

            response = new HashMap<>((Map) accountDTONew);
            http = HttpStatus.ACCEPTED;

        }catch (Exception e){
//            response new ResponseEntity<>(accountDTONew, HttpStatus.BAD_REQUEST);
            response = new HashMap<>((Map) accountDTONew);
            http = HttpStatus.BAD_REQUEST;
        }

        //que se puede enviar en el response entity
        return new ResponseEntity<>(response,null, http);
    }

    @Override
    public boolean delete(AccountDTO accountDTO) {
        boolean operation = false;

        AccountDTO accountDTONew = findById(accountDTO.getId());

        try {
            accountDTONew.setEnable(false);

            update(accountDTONew);

            operation = true;

        }catch (Exception e){
            operation = false;
        }

        return operation;

    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {

        try {
//            this.accountRepository.save(accountDTO);

        }catch (Exception e){

        }

        return null;
    }

}
