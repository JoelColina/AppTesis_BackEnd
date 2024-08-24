package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.ClientDTO;
import com.mindhub.retailhome.dtos.LoanApplicationDTO;
import com.mindhub.retailhome.dtos.LoanDTO;
import com.mindhub.retailhome.models.*;
import com.mindhub.retailhome.repositories.*;
import com.mindhub.retailhome.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<LoanDTO> findAll() {
        return this.loanRepository.findAll().stream().map(LoanDTO::new).collect(toList());
    }

    @Override
    public LoanDTO findById(long id) {
        return this.loanRepository.findById(id).map(LoanDTO::new).orElse(null);
    }
    @Override
    public ResponseEntity<Object> newRegister(LoanApplicationDTO loanApplicationDTO,
                                              Authentication authentication){
        String accExit = "1";

        ClientDTO client = clientRepository.findByEmail(authentication.getName());
//        ClientDTO clientdto = clientRepository.findByEmail(authentication.getName()).map(ClientDTO::new).orElse(null);

        Loan loan = loanRepository.findById(loanApplicationDTO.getLoanId()).orElse(null);

        Account account = accountRepository.findByNumber(loanApplicationDTO.getToAccountNumber());


        //Validamos que los parametros no esten nulos
        if (loanApplicationDTO.getAmount() == 0
                || loanApplicationDTO.getToAccountNumber().isEmpty()
                || loanApplicationDTO.getPayments() == 0) {
            return new ResponseEntity<>("Favor validar datos ingresados, no pueden haber datos nulos", HttpStatus.FORBIDDEN);
        }

        //se verifica si prestamo existe
//        for (Loan loant:client.getLoans()){//(int i = 0; i < client.getAccounts().size(); i++) {
//            if(loant.getId() == loanApplicationDTO.getLoanId()) {
//                return new ResponseEntity<>("Prestamo ya existe.", HttpStatus.FORBIDDEN);
//            }
//        }

        //se verifica que el monto solicitado no exceda el monto maximo

        assert loan != null;
        if(loanApplicationDTO.getAmount() > loan.getMaxAmount()){
            return new ResponseEntity<>("Monto solicitado excede el maximo permitido.", HttpStatus.FORBIDDEN);
        }

        //se verifica que la cantidad de cuotas se encuentre entre las disponibles del prestamo
        for (int payment: loan.getPayments()){//(int i = -1; i < client.getAccounts().size(); i++) {
            if(payment == loanApplicationDTO.getPayments()) {
                accExit = "0";
                break;
            }
        }
        if (accExit.equals("1")){
            return new ResponseEntity<>("Cuotas no permitida para prestamo seleccionado.", HttpStatus.FORBIDDEN);
        }
        accExit = "1";

        //se verificar que la cuenta de destino exista
        if (accountRepository.findByNumber(loanApplicationDTO.getToAccountNumber()) == null){
            return new ResponseEntity<>("Cuenta destino no existe.", HttpStatus.FORBIDDEN);
        }

        //se verifica que la cuenta de destino pertenezca al cliente autenticado
//        for (Account account1:client.getAccounts()){//(int i = 0; i < client.getAccounts().size(); i++) {
//            if(account1.getNumber().equals(loanApplicationDTO.getToAccountNumber())) {
//                accExit = "0";
//                break;
//            }
//        }
//        if (accExit.equals("1")){
//            return new ResponseEntity<>("Cuenta Destino no pertenece a cliente.", HttpStatus.FORBIDDEN);
//        }

       // double amountTotal = loanApplicationDTO.getAmount() + ( loanApplicationDTO.getAmount() * 0.2);

        ClientLoan clientLoan0 = new ClientLoan(loanApplicationDTO.getAmount(), loanApplicationDTO.getPayments()/*, clientdto, loan*/);

        clientLoanRepository.save(clientLoan0);
     //   transactionRepository.save(new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loan.getName() + "loan approved" , LocalDate.now(), account));
        transactionRepository.save(new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loan.getName() + "loan approved" , LocalDate.now()));
        account.setBalance(account.getBalance() + loanApplicationDTO.getAmount());
        accountRepository.save(account);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
