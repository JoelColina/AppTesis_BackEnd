package com.retailhome.models;

import com.retailhome.utils.enums.AddressType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "addresses")
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

}
