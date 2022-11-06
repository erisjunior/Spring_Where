package com.erisvan.where.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.erisvan.where.model.Store;
import com.erisvan.where.repository.StoreRepository;
import com.erisvan.where.rest.dto.StoreDTO;
import com.erisvan.where.service.StoreService;

@Component
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository repository;

    @Override
    public Store save(StoreDTO dto) {
        Store client = new Store();
        client.setName(dto.getName());
        client.setAccount(dto.getAccount());
        client.setCallings(dto.getCallings());
        return repository.save(client);
    }

    @Override
    public Optional<Store> get(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Store> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Store update(Integer id, StoreDTO dto) {
        Optional<Store> client = repository.findById(id);

        if (client.isPresent()) {
            client.get().setName(dto.getName() != null ? dto.getName() : client.get().getName());
            client.get().setAccount(dto.getAccount() != null ? dto.getAccount() : client.get().getAccount());
            client.get().setCallings(dto.getCallings() != null ? dto.getCallings() : client.get().getCallings());
            return repository.save(client.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found");
    }

}
