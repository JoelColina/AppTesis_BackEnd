package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.AccountDTO;
import com.mindhub.retailhome.mappers.AccountMapper;
import com.mindhub.retailhome.models.Account;
import com.mindhub.retailhome.repositories.AccountRepository;
import com.mindhub.retailhome.service.AccountService;
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
public class AccountServiceImpl implements AccountService {

    private AccountMapper accountMapper;
    private Map<String, Object> response;
    private HttpStatus http;
    private AccountDTO accountDtoNew;
    private AccountDTO accountDtoOld;
    private Account accountNew;

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
    public AccountDTO findAccountDto(String idClient) {
        return (AccountDTO) this.accountRepository.findAccountDto(idClient).mapToObj(AccountDTO::new).toList();
    }


    @Override
    public ResponseEntity<?> update(AccountDTO accountDTO) {

        this.response = new HashMap<>();
        this.accountDtoOld = null;
        this.accountDtoNew = null;
        this.accountNew = null;

        try {
            accountDTO = findAccountDto(accountDTO.getIdClient());

            if (accountDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;

            }else {
                accountDtoOld.setBalance(accountDTO.getBalance());
                accountDtoOld.setNumber(accountDTO.getNumber());
                accountDtoOld.setCreationDate(accountDTO.getCreationDate());

                accountNew = this.accountRepository.save(this.accountMapper.accountDtoToAccount(accountDtoOld));
                this.accountDtoNew = accountMapper.accountToAccountDto(accountRepository.save(accountNew));

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, accountDtoNew);
                http = HttpStatus.ACCEPTED;
            }

        }catch (Exception e){
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
        AccountDTO accountDtoNew = findAccountDto(accountDTO.getIdClient());
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
        accountDtoNew = null;
        accountNew = null;

        try {
            this.accountNew = this.accountRepository.save(this.accountMapper.accountDtoToAccount(accountDTO));
            this.accountNew.setEnable(true);
            this.accountDtoNew = accountMapper.accountToAccountDto(accountRepository.save(accountNew));

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
