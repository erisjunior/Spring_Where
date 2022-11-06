package com.erisvan.where.rest.controllers;

import java.util.List;
import java.util.Optional;

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

import com.erisvan.where.model.Client;
import com.erisvan.where.rest.dto.ClientDTO;
import com.erisvan.where.service.ClientService;

@RequestMapping("/api/clients")
@RestController
public class ClientController {

    @Autowired
    @Qualifier("clientServiceImpl")
    ClientService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody ClientDTO dto) {
        return service.save(dto);
    }

    @GetMapping("{id}")
    public Optional<Client> get(@PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping
    public List<Client> getAll(Client filter) {
        return service.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody ClientDTO dto) {
        service.update(id, dto);
    }

}
