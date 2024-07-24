package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.PurchasingHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditsHeaderRepository extends JpaRepository<PurchasingHeader, Long> {
}
