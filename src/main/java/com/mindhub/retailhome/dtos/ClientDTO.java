package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.Client;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClientDTO {

    private String names;
    private String lastName;
    private String motherLastName;
    private String ruth;
    private Date   birthDate;
    private Number telephoneNumber;
    private String email;
    private Number totalLimit;
    private Number debtAccount;
    private Number availableSpace;
    private String idClient;

//    Set<AccountDTO> accounts;
//    Set<ClientLoanDTO> loans;
//    Set<CardDTO> cards;
    private String password;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.names = client.getNames();
        this.lastName = client.getLastName();
        this.motherLastName = client.getMotherLastName();
        this.ruth = client.getRuth();
        this.birthDate = client.getBirthDate();
        this.telephoneNumber = client.getTelephoneNumber();
        this.email = client.getEmail();
        this.totalLimit = client.getTotalLimit();
        this.debtAccount = client.getDebtAccount();
        this.availableSpace = client.getAvailableSpace();
        this.idClient = client.getIdClient();
        this.password = client.getPassword();

//        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());
//        this.loans = client.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
//        this.cards = client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());
    }


}
