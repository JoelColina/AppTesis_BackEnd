package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getQuotaStatus() {
        return quotaStatus;
    }

    public void setQuotaStatus(String quotaStatus) {
        this.quotaStatus = quotaStatus;
    }

    public Date getPayDay() {
        return payDay;
    }

    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }

    public CreditsHeader getCreditsHeader() {
        return creditsHeader;
    }

    public void setCreditsHeader(CreditsHeader creditsHeader) {
        this.creditsHeader = creditsHeader;
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
