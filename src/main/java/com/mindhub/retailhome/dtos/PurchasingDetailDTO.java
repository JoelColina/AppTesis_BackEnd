package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.PurchasingDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchasingDetailDTO {

    private long id;
    private String product;
    private Number amount;
    private Number worth;
    private Number tax;

    public PurchasingDetailDTO(PurchasingDetail purchasingdetail) {
        this.product = purchasingdetail.getProduct();
        this.amount = purchasingdetail.getAmount();
        this.worth = purchasingdetail.getWorth();
        this.tax = purchasingdetail.getTax();
    }

    public PurchasingDetailDTO() {
    }

}
