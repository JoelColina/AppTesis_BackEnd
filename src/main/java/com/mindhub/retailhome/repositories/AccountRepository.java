package com.mindhub.retailhome.repositories;

import com.mindhub.retailhome.models.Account;
import com.mindhub.retailhome.models.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByNumber(String inputCta);

    List<Account> findAccountByClient(String idClient);
}
