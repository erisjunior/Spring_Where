package com.erisvan.where.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erisvan.where.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);
}
