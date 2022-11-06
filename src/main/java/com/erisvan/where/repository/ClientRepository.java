package com.erisvan.where.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erisvan.where.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
