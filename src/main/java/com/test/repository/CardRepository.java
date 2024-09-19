package com.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
