package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.ClientDTO;
import com.mindhub.retailhome.dtos.LoanApplicationDTO;
import com.mindhub.retailhome.dtos.LoanDTO;
import com.mindhub.retailhome.dtos.PurchasingDetailDTO;
import com.mindhub.retailhome.mappers.LoanMapper;
import com.mindhub.retailhome.mappers.PurchasingDetailMapper;
import com.mindhub.retailhome.models.*;
import com.mindhub.retailhome.repositories.*;
import com.mindhub.retailhome.service.LoanService;
import com.mindhub.retailhome.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
public class LoanServiceImpl implements LoanService {
    private Map<String, Object> response;
    private HttpStatus http;
    private LoanRepository loanRepository;
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
    private ClientLoanRepository clientLoanRepository;
    private TransactionRepository transactionRepository;
    private LoanMapper loanMapper;

    LoanServiceImpl(LoanRepository loanRepository,
                    ClientRepository clientRepository,
                    AccountRepository accountRepository,
                    ClientLoanRepository clientLoanRepository,
                    TransactionRepository transactionRepository) {

        this.loanRepository = loanRepository;
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.clientLoanRepository = clientLoanRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ResponseEntity<?> finAll() {
        response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        List<LoanDTO> listDto = new ArrayList<>();

        try {
            this.loanRepository.findAll().forEach(Loan ->
                    listDto.add(this.loanMapper.toLoanDto(Loan))
            );
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.PURCHASING_DETAIL.PURCHASING_DETAILS, listDto);
            this.http = HttpStatus.ACCEPTED;

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(listDto, this.http);
    }

    @Override
    public LoanDTO findById(Long id) {
        this.response = new HashMap<>();
        this.http = HttpStatus.NOT_FOUND;
        if (id == null) {
            return this.loanRepository.findById(id).map(LoanDTO::new).orElse(null);
        }
        return new LoanDTO();
    }

    @Override
    public ResponseEntity<Object> newRegister(LoanApplicationDTO loanApplicationDTO,
                                              Authentication authentication){
        String accExit = "1";

      //  ClientDTO client = clientRepository.findByEmail(authentication.getName());
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

//        ClientLoan clientLoan0 = new ClientLoan(loanApplicationDTO.getAmount(), loanApplicationDTO.getPayments()/*, clientdto, loan*/);

//        clientLoanRepository.save(clientLoan0);
     //   transactionRepository.save(new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loan.getName() + "loan approved" , LocalDate.now(), account));
//        transactionRepository.save(new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loan.getName() + "loan approved" , LocalDate.now()));
        account.setBalance(account.getBalance() + loanApplicationDTO.getAmount());
        accountRepository.save(account);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
