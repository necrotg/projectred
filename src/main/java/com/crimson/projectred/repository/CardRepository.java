package com.crimson.projectred.repository;

import com.crimson.projectred.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<List<Card>> findCardByCustomer_CustomerId(Long customerId);
}
