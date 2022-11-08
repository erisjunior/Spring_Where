package com.erisvan.where.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erisvan.where.model.Calling;
import com.erisvan.where.model.Category;

public interface CallingRepository extends JpaRepository<Calling, Integer> {
  List<Calling> findAllByCategory(Category category);
}
