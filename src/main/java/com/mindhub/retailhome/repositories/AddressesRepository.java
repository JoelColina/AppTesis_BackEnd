package com.mindhub.retailhome.repositories;

import com.mindhub.retailhome.models.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AddressesRepository extends JpaRepository<Addresses, Long> {
}
