package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Lists;


public interface ListRepository extends JpaRepository<Lists, Integer> {
}
