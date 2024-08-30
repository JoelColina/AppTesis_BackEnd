package com.mindhub.retailhome.repositories;

import com.mindhub.retailhome.models.PurchasingHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.stream.DoubleStream;

@RepositoryRestResource
public interface PurchasingHeaderRepository extends JpaRepository<PurchasingHeader, Long> {
    DoubleStream PurchasingHeaderDto(String idClient);
}
