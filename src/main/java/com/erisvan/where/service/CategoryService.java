package com.erisvan.where.service;

import java.util.List;
import java.util.Optional;

import com.erisvan.where.model.Category;
import com.erisvan.where.rest.dto.CategoryDTO;

public interface CategoryService {
    Category save(CategoryDTO dto);

    Optional<Category> get(Integer id);

    List<Category> getAll();

    void delete(Integer id);

    Category update(Integer id, CategoryDTO dto);

}
