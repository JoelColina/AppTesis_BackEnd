package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.PurchasingDetailDTO;
import com.mindhub.homebanking.repositories.PurchasingDetailRepository;
import com.mindhub.homebanking.service.PurchasingDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class PurchasingDetailServiceImpl implements PurchasingDetailService {

    @Autowired
    private PurchasingDetailRepository purchasingdetailrepository;

    @Override
    public Set<PurchasingDetailDTO> finAll() {
        return Set.of();
    }

    @Override
    public PurchasingDetailDTO finById(Long id) {
        return null;
    }
}
