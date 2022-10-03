package com.erisvan.where.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erisvan.where.model.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
