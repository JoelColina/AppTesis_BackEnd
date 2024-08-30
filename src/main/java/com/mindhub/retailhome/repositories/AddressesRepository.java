package com.mindhub.retailhome.repositories;

import com.mindhub.retailhome.dtos.AddressesDTO;
import com.mindhub.retailhome.models.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.stream.DoubleStream;

@RepositoryRestResource
public interface AddressesRepository extends JpaRepository<Addresses, Long> {

    DoubleStream finAddressesDto(String idClient);

    DoubleStream findAccountDto(String idClient);
}
