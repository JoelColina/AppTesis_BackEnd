package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
}
