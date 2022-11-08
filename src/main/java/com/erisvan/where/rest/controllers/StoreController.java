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

import com.erisvan.where.model.Store;
import com.erisvan.where.rest.dto.StoreDTO;
import com.erisvan.where.service.StoreService;

@RequestMapping("/api/stores")
@RestController
public class StoreController {

    @Autowired
    @Qualifier("storeServiceImpl")
    StoreService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Store save(@RequestBody StoreDTO dto) {
        return service.save(dto);
    }

    @GetMapping("{id}")
    public Store get(@PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping
    public List<Store> getAll(Store filter) {
        return service.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody StoreDTO dto) {
        service.update(id, dto);
    }

}
