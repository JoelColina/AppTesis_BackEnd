package com.retailhome.repositories;

import com.retailhome.models.PurchasingHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PurchasingHeaderRepository extends JpaRepository<PurchasingHeader, Long> {
}
