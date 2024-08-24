package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.PurchasingDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchasingDetailDTO {

    private String product;
    private Number amount;
    private Number worth;
    private Number tax;
    private long idPurchasing;

    public PurchasingDetailDTO(PurchasingDetail purchasingdetail) {
        this.product = purchasingdetail.getProduct();
        this.amount = purchasingdetail.getAmount();
        this.worth = purchasingdetail.getWorth();
        this.tax = purchasingdetail.getTax();
        this.idPurchasing = purchasingdetail.getId();
    }

    public PurchasingDetailDTO() {
    }

}
