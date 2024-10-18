package com.mindhub.retailhome.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "credits_header")
public class CreditsHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "creditsHeader", fetch = FetchType.EAGER)
    Set<CreditDetail> creditDetails = new HashSet<>();

    private Number requestedAmount;
    private Number quotaNumber;
    private boolean enabled;
    private long idClient;





}
