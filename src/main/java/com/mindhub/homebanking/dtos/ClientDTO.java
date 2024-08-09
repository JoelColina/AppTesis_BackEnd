package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Client;

import java.util.Date;

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

//    Set<AccountDTO> accounts;
//    Set<ClientLoanDTO> loans;
//    Set<CardDTO> cards;
    private String password;

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

        this.password = client.getPassword();

//        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());
//        this.loans = client.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
//        this.cards = client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());
    }



    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getRuth() {
        return ruth;
    }

    public void setRuth(String ruth) {
        this.ruth = ruth;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Number getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Number telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Number getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Number totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Number getDebtAccount() {
        return debtAccount;
    }

    public void setDebtAccount(Number debtAccount) {
        this.debtAccount = debtAccount;
    }

    public Number getAvailableSpace() {
        return availableSpace;
    }

    public void setAvailableSpace(Number availableSpace) {
        this.availableSpace = availableSpace;
    }

//    public Set<AccountDTO> getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(Set<AccountDTO> accounts) {
//        this.accounts = accounts;
//    }
//
//    public Set<ClientLoanDTO> getLoans() {
//        return loans;
//    }
//
//    public void setLoans(Set<ClientLoanDTO> loans) {
//        this.loans = loans;
//    }
//
//    public Set<CardDTO> getCards() {
//        return cards;
//    }
//
//    public void setCards(Set<CardDTO> cards) {
//        this.cards = cards;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
       this.password = password;
    }
}
