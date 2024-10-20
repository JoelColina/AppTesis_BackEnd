package com.retailhome.repositories;

import com.retailhome.models.CreditsHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CreditsHeaderRepository extends JpaRepository<CreditsHeader, Long> {
}
