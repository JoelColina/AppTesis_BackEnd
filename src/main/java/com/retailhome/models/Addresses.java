package com.retailhome.models;

import com.retailhome.utils.enums.AddressType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
}
