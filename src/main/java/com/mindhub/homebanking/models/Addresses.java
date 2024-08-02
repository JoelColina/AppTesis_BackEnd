package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    private String address;
    private Number number;
    private String city;
    private String commune;
    private Number postalCode;
    private AddressType type;
    private boolean enabled;

    public Addresses(String address, Number number, String city, String commune, Number postalCode, AddressType type, boolean enabled) {

        this.address = address;
        this.number = number;
        this.city = city;
        this.commune = commune;
        this.postalCode = postalCode;
        this.type = type;
        this.enabled = enabled;
    }

    public Addresses() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public Number getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Number postalCode) {
        this.postalCode = postalCode;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Addresses{" +
//                "idClient=" + idClient +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", commune='" + commune + '\'' +
                ", postalCode=" + postalCode +
                ", type=" + type +
                ", enabled=" + enabled +
                '}';
    }
}
