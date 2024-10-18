package com.mindhub.retailhome.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
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



    public CreditDetail() {
    }

    public CreditDetail(long id, Date dateExpiration, String quotaStatus, Date payDay, CreditsHeader creditsHeader, Client client) {
        this.id = id;
        this.dateExpiration = dateExpiration;
        this.quotaStatus = quotaStatus;
        this.payDay = payDay;
        this.creditsHeader = creditsHeader;
    }

    @Override
    public String toString() {
        return "CreditDetail{" +
                "id=" + id +
                ", dateExpiration=" + dateExpiration +
                ", quotaStatus='" + quotaStatus + '\'' +
                ", payDay=" + payDay +
                ", creditsHeader=" + creditsHeader +
                '}';
    }
}
