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

import com.erisvan.where.model.Avatar;
import com.erisvan.where.rest.dto.AvatarDTO;
import com.erisvan.where.service.AvatarService;

@RequestMapping("/api/avatars")
@RestController
public class AvatarController {

    @Autowired
    @Qualifier("avatarServiceImpl")
    AvatarService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Avatar save(@RequestBody AvatarDTO dto) {
        return service.save(dto);
    }

    @GetMapping("{id}")
    public Avatar get(@PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping
    public List<Avatar> getAll(Avatar filter) {
        // ExampleMatcher matcher = ExampleMatcher
        // .matching()
        // .withIgnoreCase()
        // .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        // Example<Avatar> example = Example.of(filter, matcher);
        return service.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody AvatarDTO dto) {
        service.update(id, dto);
    }

}
