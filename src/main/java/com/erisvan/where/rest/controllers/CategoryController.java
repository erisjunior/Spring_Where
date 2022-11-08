package com.erisvan.where.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erisvan.where.model.Category;
import com.erisvan.where.rest.dto.CategoryDTO;
import com.erisvan.where.service.CategoryService;

@RequestMapping("/api/categories")
@RestController
public class CategoryController {

    @Autowired
    @Qualifier("categoryServiceImpl")
    CategoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category save(@RequestBody CategoryDTO dto) {
        return service.save(dto);
    }

    @GetMapping("{id}")
    public Category get(@PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping
    public List<Category> getAll(Category filter) {
        return service.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody CategoryDTO dto) {
        service.update(id, dto);
    }

}
