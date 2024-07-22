package com.mindhub.homebanking.models;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;
    private Date   fechaNacimiento;
    private Number numeroTelefono;
    private String email;
    private Number cupoTotal;
    private Number deudaCta;
    private Number cupoDisponible;

        @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<Card> cards = new HashSet<>();

    private String password;

    public Client(String nombres, String apellidoPaterno, String apellidoMaterno, String rut, Date fechaNacimiento, Number numeroTelefono, String email, Number cupoTotal, Number deudaCta, Number cupoDisponible, String password) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rut = rut;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroTelefono = numeroTelefono;
        this.cupoTotal = cupoTotal;
        this.deudaCta = deudaCta;
        this.cupoDisponible = cupoDisponible;
        this.email = email;
   //     this.accounts = accounts;
        this.password = password;


    }

    public Client(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccounts(Account account) {
        account.setClient(this);
        this.accounts.add(account);
    }

    @JsonIgnore
    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public void addClientLoans(ClientLoan clientLoan){
        clientLoan.setClient(this);
        clientLoans.add(clientLoan);
    }

    public List<Loan> getLoans(){
        return clientLoans.stream().map(ClientLoan::getLoan).collect(Collectors.toList());
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nombres='" + nombres + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", rut='" + rut + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", numeroTelefono=" + numeroTelefono +
                ", email='" + email + '\'' +
                ", cupoTotal=" + cupoTotal +
                ", deudaCta=" + deudaCta +
                ", cupoDisponible=" + cupoDisponible +
                '}';
    }
}

