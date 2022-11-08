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

import com.erisvan.where.model.Calling;
import com.erisvan.where.rest.dto.CallingDTO;
import com.erisvan.where.service.CallingService;

@RequestMapping("/api/callings")
@RestController
public class CallingController {

    @Autowired
    @Qualifier("callingServiceImpl")
    CallingService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Calling save(@RequestBody CallingDTO dto) {
        return service.save(dto);
    }

    @GetMapping("{id}")
    public Calling get(@PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping
    public List<Calling> getAll(Calling filter) {
        return service.getAll();
    }

    @GetMapping("category/{categoryId}")
    public List<Calling> getByCategory(@PathVariable Integer categoryId) {
        return service.getByCategory(categoryId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody CallingDTO dto) {
        service.update(id, dto);
    }

}
