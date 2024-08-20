package com.mindhub.retailhome.repositories;

import com.mindhub.retailhome.models.PurchasingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PurchasingDetailRepository extends JpaRepository<PurchasingDetail, Long> {
}
