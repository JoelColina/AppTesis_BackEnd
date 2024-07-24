package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.AddressType;

public class AddressesDTO {

    private long id;
    private long idClient;
    private String address;
    private Number number;
    private String city;
    private String commune;
    private Number postalCode;
    private AddressType type;
    private boolean enabled;

    public AddressesDTO(long idClient, String address, Number number, String city, String commune, Number postalCode, AddressType type, boolean enabled) {
        this.idClient = idClient;
        this.address = address;
        this.number = number;
        this.city = city;
        this.commune = commune;
        this.postalCode = postalCode;
        this.type = type;
        this.enabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
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
}
