package com.crimson.projectred.repository;

import com.crimson.projectred.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
