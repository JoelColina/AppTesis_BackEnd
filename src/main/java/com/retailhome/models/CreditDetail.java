package com.retailhome.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "credit_detail")
public class CreditDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date dateExpiration;

    private String quotaStatus;

    private Date payDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id")
    private CreditsHeader creditsHeader;

}
