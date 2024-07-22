package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AddressesRepository extends JpaRepository<Addresses, Long> {
}
