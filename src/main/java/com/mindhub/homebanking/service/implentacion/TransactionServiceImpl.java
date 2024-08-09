package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Set<TransactionDTO> findAll() {
        return this.transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toSet());
    }

    @Override
    public TransactionDTO findById(Long id) {
        return this.transactionRepository.findById(id).map(TransactionDTO::new).orElse(null);
    }

    @Override
    public ResponseEntity<Object> newTransaction(Integer amount, String description, String fromAccount, String toAccount, Authentication authentication) {

        String accExit = "0";

        ClientDTO clientdto = clientRepository.findByEmail(authentication.getName()).map(ClientDTO::new).orElse(null);

        //valida variable fromAccount si viene vacio
        Account accountOrig = accountRepository.findByNumber(fromAccount);

        if (accountOrig == null){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        //valida variable toAccount si viene vacio
        Account accountDestin = accountRepository.findByNumber(toAccount);

        if (accountDestin == null){
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

        //se valida que la cuenta origen sea del cliente logeado
//        for (Account account1:client.getAccounts()){//(int i = 0; i < client.getAccounts().size(); i++) {
//            if(account1.getNumber().equals(fromAccount)) {
//                accExit = "1";
//                break;
//            }
//        }

        if (accExit.equals("0")){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        //se valida que la cuenta destino exista
        if (accountRepository.findByNumber(toAccount) == null){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        //se valida que la cta origen tenga saldo suficiente
        if (accountOrig.getBalance() < amount){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        transactionRepository.save(new Transaction(TransactionType.DEBIT, -amount, description + " " + toAccount +" .", LocalDate.now(), accountOrig));
        transactionRepository.save(new Transaction(TransactionType.CREDIT, amount, description + " " + fromAccount +" .", LocalDate.now(), accountDestin));

        accountOrig.setBalance(accountOrig.getBalance() - amount);
        accountDestin.setBalance(accountDestin.getBalance() + amount);

        accountRepository.save(accountOrig);
        accountRepository.save(accountDestin);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
