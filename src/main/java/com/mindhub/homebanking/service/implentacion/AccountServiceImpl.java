package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.service.AccountService;
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
public class AccountServiceImpl implements AccountService {

    private Map<String, Object> response;
    private HttpStatus http;
    private AccountDTO accountDtoNew;

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
        this.accountDtoNew = null;

        try {
            accountDtoNew = this.accountRepository.findById(accountDTO.getId()).map(AccountDTO::new).orElse(null);

            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, accountDtoNew);
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

    @Override
    public boolean delete(AccountDTO accountDTO) {
        boolean operation = false;
        AccountDTO accountDtoNew = findById(accountDTO.getId());

        try {
            accountDtoNew.setEnable(false);
            update(accountDtoNew);
            operation = true;
        }catch (Exception e){
            operation = false;
        }

        return operation;

    }

    @Override
    public ResponseEntity<?> save(AccountDTO accountDTO) {
        this.response = new HashMap<>();
        accountDtoNew = accountDTO;
        try {
//            this.accountRepository.save(accountDTO);
            this.accountDtoNew.setEnable(true);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, accountDtoNew);
            this.http = HttpStatus.CREATED;

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response, this.http);
    }

}
