package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.AddressType;
import com.mindhub.homebanking.models.Addresses;

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

//    public AddressesDTO(long idClient, String address, Number number, String city, String commune, Number postalCode, AddressType type, boolean enabled) {
     public AddressesDTO(Addresses addresses){
        this.idClient = addresses.getIdClient();
        this.address = addresses.getAddress();
        this.number = addresses.getNumber();
        this.city = addresses.getCity();
        this.commune = addresses.getCommune();
        this.postalCode = addresses.getPostalCode();
        this.type = addresses.getType();
        this.enabled = addresses.isEnabled();
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
