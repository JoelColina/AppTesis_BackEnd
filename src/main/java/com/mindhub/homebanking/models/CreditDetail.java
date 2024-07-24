package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class CreditDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private long idCredit;
    private Date dateExpiration;
    private String quotaStatus;
    private Date payDay;

    public CreditDetail(long idCredit, Date dateExpiration, String quotaStatus, Date payDay) {
        this.idCredit = idCredit;
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

    public long getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(long idCredit) {
        this.idCredit = idCredit;
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

    @Override
    public String toString() {
        return "CreditDetail{" +
                "idCredit=" + idCredit +
                ", dateExpiration=" + dateExpiration +
                ", quotaStatus='" + quotaStatus + '\'' +
                ", payDay=" + payDay +
                '}';
    }
}
