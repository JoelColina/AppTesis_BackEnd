package com.mindhub.retailhome.repositories;

import com.mindhub.retailhome.models.CreditDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CreditDetailRepository extends JpaRepository<CreditDetail, Long> {
}
