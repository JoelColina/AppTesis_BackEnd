package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Client;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private Long id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;
    private Date fechaNacimiento;
    private Number numeroTelefono;
    private String email;
    private Number cupoTotal;
    private Number deudaCta;
    private Number cupoDisponible;

    Set<AccountDTO> accounts;
    Set<ClientLoanDTO> loans;
    Set<CardDTO> cards;

    private String password;


    public ClientDTO(Client client) {

        this.nombres = client.getNombres();
        this.apellidoPaterno = client.getApellidoPaterno();
        this.apellidoMaterno = client.getApellidoMaterno();
        this.rut = client.getRut();
        this.fechaNacimiento = client.getFechaNacimiento();
        this.numeroTelefono = client.getNumeroTelefono();
        this.email = client.getEmail();
        this.cupoTotal = client.getCupoTotal();
        this.deudaCta = client.getDeudaCta();
        this.cupoDisponible = client.getCupoDisponible();

        this.password = client.getPassword();

        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());
        this.loans = client.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
        this.cards = client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Number getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(Number numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Number getCupoTotal() {
        return cupoTotal;
    }

    public void setCupoTotal(Number cupoTotal) {
        this.cupoTotal = cupoTotal;
    }

    public Number getDeudaCta() {
        return deudaCta;
    }

    public void setDeudaCta(Number deudaCta) {
        this.deudaCta = deudaCta;
    }

    public Number getCupoDisponible() {
        return cupoDisponible;
    }

    public void setCupoDisponible(Number cupoDisponible) {
        this.cupoDisponible = cupoDisponible;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public Set<ClientLoanDTO> getLoans() {
        return loans;
    }

    public void setLoans(Set<ClientLoanDTO> loans)
    {
        this.loans = loans;
    }

    public Set<CardDTO> getCards() {
        return cards;
    }

    public void setCards(Set<CardDTO> cards) {
        this.cards = cards;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
       this.password = password;
    }
}
