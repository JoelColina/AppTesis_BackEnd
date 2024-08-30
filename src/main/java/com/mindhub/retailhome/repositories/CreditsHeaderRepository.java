package com.mindhub.retailhome.repositories;

import com.mindhub.retailhome.models.CreditsHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.stream.DoubleStream;

@RepositoryRestResource
public interface CreditsHeaderRepository extends JpaRepository<CreditsHeader, Long> {
    DoubleStream findAccountDto(String idClient);
}
