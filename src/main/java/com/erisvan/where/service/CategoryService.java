package com.erisvan.where.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erisvan.where.model.Category;
import com.erisvan.where.rest.dto.CategoryDTO;

@Service
public interface CategoryService {
    Category save(CategoryDTO dto);

    Category get(Integer id);

    List<Category> getAll();

    void delete(Integer id);

    Category update(Integer id, CategoryDTO dto);

}
