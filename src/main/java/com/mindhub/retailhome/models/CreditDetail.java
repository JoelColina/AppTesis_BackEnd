package com.mindhub.retailhome.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class CreditDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id")
    private CreditsHeader creditsHeader;

     private Date dateExpiration;
    private String quotaStatus;
    private Date payDay;

    public CreditDetail(Date dateExpiration, String quotaStatus, Date payDay) {
        this.dateExpiration = dateExpiration;
        this.quotaStatus = quotaStatus;
        this.payDay = payDay;
    }

    public CreditDetail() {
    }


    @Override
    public String toString() {
        return "CreditDetail{" +
                ", dateExpiration=" + dateExpiration +
                ", quotaStatus='" + quotaStatus + '\'' +
                ", payDay=" + payDay +
                '}';
    }
}
