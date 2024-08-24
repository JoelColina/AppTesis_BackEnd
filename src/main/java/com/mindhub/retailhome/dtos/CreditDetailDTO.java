package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.CreditDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreditDetailDTO {

    private Date dateExpiration;
    private String quotaStatus;
    private Date payDay;
    private long idCredit;

    public CreditDetailDTO(CreditDetail creditdetail) {
        this.dateExpiration = creditdetail.getDateExpiration();
        this.quotaStatus = creditdetail.getQuotaStatus();
        this.payDay = creditdetail.getPayDay();
        this.idCredit = creditdetail.getId();
    }

    public CreditDetailDTO() {
    }

}
