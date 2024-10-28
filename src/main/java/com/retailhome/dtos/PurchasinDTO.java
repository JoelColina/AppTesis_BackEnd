package com.retailhome.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class PurchasinDTO {

    private PurchasingHeaderDTO purchasingheaderDTO;
    private PurchasingDetailDTO purchasingdetailDTO;
}
