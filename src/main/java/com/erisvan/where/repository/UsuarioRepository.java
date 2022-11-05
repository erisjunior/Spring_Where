package com.erisvan.where.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erisvan.where.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);
}
