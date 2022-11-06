package com.erisvan.where.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erisvan.where.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByLogin(String login);
}
