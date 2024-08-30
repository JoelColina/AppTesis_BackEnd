package com.mindhub.retailhome.repositories;

import com.mindhub.retailhome.dtos.AccountDTO;
import com.mindhub.retailhome.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.stream.DoubleStream;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByNumber(String inputCta);

    DoubleStream findAccountDto(String idClient);
}
