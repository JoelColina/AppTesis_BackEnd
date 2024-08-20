package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.CreditDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreditDetailDTO {

    private long id;

    private Date dateExpiration;
    private String quotaStatus;
    private Date payDay;

    public CreditDetailDTO(CreditDetail creditdetail) {
        this.dateExpiration = creditdetail.getDateExpiration();
        this.quotaStatus = creditdetail.getQuotaStatus();
        this.payDay = creditdetail.getPayDay();
    }

    public CreditDetailDTO() {
    }

}
