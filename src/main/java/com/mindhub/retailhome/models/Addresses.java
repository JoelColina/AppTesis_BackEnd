package com.mindhub.retailhome.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idClient")
    private Client client;

    private String address;
    private Number number;
    private String city;
    private String commune;
    private Number postalCode;
    private AddressType type;
    private boolean enabled;
    private String idClient;

    public Addresses(String address, Number number, String city, String commune, Number postalCode, AddressType type, boolean enabled) {

        this.address = address;
        this.number = number;
        this.city = city;
        this.commune = commune;
        this.postalCode = postalCode;
        this.type = type;
        this.enabled = enabled;
        this.idClient = client.getIdClient();
    }

    public Addresses() {
    }

    @Override
    public String toString() {
        return "Addresses{" +
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
