package com.erisvan.where.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erisvan.where.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
