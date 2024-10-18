package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.ClientDTO;
import com.mindhub.retailhome.dtos.TransactionDTO;
import com.mindhub.retailhome.mappers.ClientMapper;
import com.mindhub.retailhome.mappers.TransactionMapper;
import com.mindhub.retailhome.models.Account;
import com.mindhub.retailhome.models.Transaction;
import com.mindhub.retailhome.repositories.AccountRepository;
import com.mindhub.retailhome.repositories.ClientRepository;
import com.mindhub.retailhome.repositories.TransactionRepository;
import com.mindhub.retailhome.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class TransactionServiceImpl implements TransactionService {


    private TransactionRepository transactionRepository;
    private TransactionDTO transactionDto;
    private ClientDTO clientDto;
    private Account accountOrig;
    private Account accountDestin;
    private TransactionMapper transactionMapper;
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;


    private AccountRepository accountRepository;

    TransactionServiceImpl(TransactionRepository transactionRepository,
                           ClientRepository clientRepository,
                           AccountRepository accountRepository,
                           TransactionDTO transactionDto,
                           TransactionMapper transactionMapper,
                           Account accountDestin,
                           Account accountOrig
    ){}

    @Override
    public Set<TransactionDTO> findAll() {
        return Collections.singleton(this.transactionMapper.transactionToTransactionDto(Optional.of((Transaction) this.transactionRepository.findAll())));
    }

    @Override
    public TransactionDTO findById(Long id) {
        return this.transactionMapper.transactionToTransactionDto( this.transactionRepository.findById(id));
    }

    @Override
    public ResponseEntity<Object> newTransaction(Integer amount, String description, String fromAccount, String toAccount, Authentication authentication) {
        this.accountDestin = new Account() ;
        this.accountOrig = new Account() ;
        String accExit = "0";

         clientDto = this.clientMapper.clientToClientDto( clientRepository.findByEmail(authentication.getName()));


        //valida variable fromAccount si viene vacio
          this.accountOrig = accountRepository.findByNumber(fromAccount);

        if (this.accountOrig == null){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        //valida variable toAccount si viene vacio
        Account accountDestin1 = accountRepository.findByNumber(toAccount);

        if (accountDestin1 == null){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        //Validamos que los parametros no esten nulos
        if (amount.equals(0) || description.isEmpty() || fromAccount.isEmpty() || toAccount.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

       //se valida que los numeros de ctas no sean iguales
        if (fromAccount.equals(toAccount)) {
            return new ResponseEntity<>("missing data", HttpStatus.FORBIDDEN);
        }


        if (accExit.equals("0")){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        //se valida que la cuenta destino exista
        if (this.accountRepository.findByNumber(toAccount) == null){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (this.accountOrig.getBalance() < amount){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        this.accountOrig.setBalance(this.accountOrig.getBalance() - amount);
        accountDestin1.setBalance(accountDestin1.getBalance() + amount);

        this.accountRepository.save(accountOrig);
        this.accountRepository.save(accountDestin);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
