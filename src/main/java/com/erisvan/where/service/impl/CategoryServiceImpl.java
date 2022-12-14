package com.erisvan.where.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.erisvan.where.model.Category;
import com.erisvan.where.repository.CategoryRepository;
import com.erisvan.where.rest.dto.CategoryDTO;
import com.erisvan.where.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository repository;

    @Override
    public Category save(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return repository.save(category);
    }

    @Override
    public Category get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Category update(Integer id, CategoryDTO dto) {
        Optional<Category> category = repository.findById(id);

        if (category.isPresent()) {
            category.get().setName(dto.getName() != null ? dto.getName() : category.get().getName());
            return repository.save(category.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
    }

}
