package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

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

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<Addresses> addresses = new HashSet<>();
//
//    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
//    Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<Card> cards = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<CreditsHeader> creditsHeaders = new HashSet<>();

    private String password;

    public Client(String names, String lastName, String motherLastName, String ruth, Date birthDate, Number telephoneNumber, String email, Number totalLimit, Number debtAccount, Number availableSpace, String password) {
        this.names = names;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.ruth = ruth;
        this.birthDate = birthDate;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.totalLimit = totalLimit;
        this.debtAccount = debtAccount;
        this.availableSpace = availableSpace;
        this.password = password;
    }

    public Client() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Addresses> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Addresses> addresses) {
        this.addresses = addresses;
    }

    //    @JsonIgnore
//    public Set<ClientLoan> getClientLoans() {
//        return clientLoans;
//    }
//
//    public void addClientLoans(ClientLoan clientLoan){
//        clientLoan.setClient(this);
//        clientLoans.add(clientLoan);
//    }
//
//    public List<Loan> getLoans(){
//        return clientLoans.stream().map(ClientLoan::getLoan).collect(Collectors.toList());
//    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public Set<CreditsHeader> getCreditsHeaders() {
        return creditsHeaders;
    }

    public void setCreditsHeaders(Set<CreditsHeader> creditsHeaders) {
        this.creditsHeaders = creditsHeaders;
    }

    @Override
    public String toString() {
        return "Client{" +
                "names='" + names + '\'' +
                ", lastName='" + lastName + '\'' +
                ", motherLastName='" + motherLastName + '\'' +
                ", ruth='" + ruth + '\'' +
                ", birthDate=" + birthDate +
                ", telephoneNumber=" + telephoneNumber +
                ", email='" + email + '\'' +
                ", totalLimit=" + totalLimit +
                ", debtAccount=" + debtAccount +
                ", availableSpace=" + availableSpace +
                ", password='" + password + '\'' +
                '}';
    }
}

