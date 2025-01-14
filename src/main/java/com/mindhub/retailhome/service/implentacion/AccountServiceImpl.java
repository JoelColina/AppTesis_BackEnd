package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.AccountDTO;
import com.mindhub.retailhome.mappers.AccountMapper;
import com.mindhub.retailhome.models.Account;
import com.mindhub.retailhome.repositories.AccountRepository;
import com.mindhub.retailhome.service.AccountService;
import com.mindhub.retailhome.utils.Constants;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    private Map<String, Object> response;
    private HttpStatus http;
    private AccountDTO accountDtoNew;
    private Account accountNew;
    private AccountMapper accountMapper;
    private final AccountMapper mapper = Mappers.getMapper(AccountMapper.class);

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    private final AccountRepository accountRepository;

    @Override
    public ResponseEntity<?> findAll(){
        response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        List<AccountDTO> listDto = new ArrayList<>();

        try {
            this.accountRepository.findAll().forEach(account ->
                listDto.add(this.accountMapper.toAccountDto(account)));

            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.ACCOUNT.ACCOUNTS, listDto);
            this.http = HttpStatus.ACCEPTED;
        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(listDto, this.http);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        response = new HashMap<>();
        this.accountDtoNew = new AccountDTO();
        this.http = HttpStatus.NOT_FOUND;
        try {
            if (id != null) {
                this.accountDtoNew = mapper.toAccountDto(accountRepository.findById(id).orElse(null));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.ACCOUNT.ACCOUNT,  this.accountDtoNew);
                this.http = HttpStatus.ACCEPTED;
            } else {
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, this.http);
    }

    @Override
    public ResponseEntity<?> update(AccountDTO accountDTO) {
        this.response = new HashMap<>();
        this.accountDtoNew = new AccountDTO();
        this.accountNew = new Account();
        this.http = HttpStatus.NOT_FOUND;

        try{
            this.accountNew = this.accountRepository.findById(accountDTO.getIdClient()).orElse(null);
            this.accountDtoNew = mapper.toAccountDto(this.accountNew);
            if (this.accountNew == null){
                this.response.put(Constants.GEMERAL.ERROR,Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else{
                accountDtoNew.setBalance(accountDTO.getBalance());
                accountDtoNew.setNumber(accountDTO.getNumber());
                accountDtoNew.setCreationDate(accountDTO.getCreationDate());

                accountNew = this.accountRepository.save(this.mapper.toAccount(accountDtoNew));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, accountNew);
                this.http = HttpStatus.ACCEPTED;
            }

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response, this.http);

    }

    @Override
    public ResponseEntity<?> delete(AccountDTO accountDTO) {
       this.response = new HashMap<>();
       this.http = HttpStatus.NOT_FOUND;
       this.accountDtoNew = new AccountDTO();
       this.accountNew = new Account();

       try{
           this.accountNew = this.accountRepository.findByNumber(accountDTO.getNumber());

           if(this.accountNew == null){
              this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
              this.http = HttpStatus.CONFLICT;
           }else{
              this.accountNew.setActive(false);
              this.accountRepository.save(this.accountNew);
              this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
              this.http = HttpStatus.ACCEPTED;
           }
           this.accountNew.setActive(false);
           update(accountDtoNew);

       }catch (Exception e){
           this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
           this.response.put(Constants.GEMERAL.ERROR,e.getMessage());
           http = HttpStatus.BAD_REQUEST;
       }
        return new ResponseEntity<>(this.response,this.http);
    }

    @Override
    public ResponseEntity<?> save(AccountDTO accountDTO) {
       this.response = new HashMap<>();
       accountDtoNew = accountDTO;
       accountNew = null;

       try {
           this.accountNew = this.accountRepository.save(this.mapper.toAccount(accountDTO));
           this.accountNew.setActive(true);
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
