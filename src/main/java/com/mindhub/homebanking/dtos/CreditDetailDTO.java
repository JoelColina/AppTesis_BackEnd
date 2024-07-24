package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.CreditDetail;

import java.util.Date;

public class CreditDetailDTO {

    private long id;

    private long idCredit;
    private Date dateExpiration;
    private String quotaStatus;
    private Date payDay;

    public CreditDetailDTO(CreditDetail creditdetail) {
        this.idCredit = creditdetail.getIdCredit();
        this.dateExpiration = creditdetail.getDateExpiration();
        this.quotaStatus = creditdetail.getQuotaStatus();
        this.payDay = creditdetail.getPayDay();
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
}
