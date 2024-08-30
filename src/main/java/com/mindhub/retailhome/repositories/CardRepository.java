package com.mindhub.retailhome.repositories;

import com.mindhub.retailhome.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.stream.DoubleStream;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {
    DoubleStream findCardDto(String idClient);
}
