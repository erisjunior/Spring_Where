package com.erisvan.where.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.erisvan.where.exception.BusinessException;
import com.erisvan.where.model.Account;
import com.erisvan.where.model.Category;
import com.erisvan.where.model.Store;
import com.erisvan.where.repository.AccountRepository;
import com.erisvan.where.repository.CategoryRepository;
import com.erisvan.where.repository.StoreRepository;
import com.erisvan.where.rest.dto.StoreDTO;
import com.erisvan.where.service.StoreService;

@Component
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository repository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Store save(StoreDTO dto) {
        Store store = new Store();
        store.setName(dto.getName());

        Account account = accountRepository
                .findById(dto.getAccountId())
                .orElseThrow(() -> new BusinessException("Invalid account id."));

        store.setAccount(account);

        Category category = categoryRepository
                .findById(dto.getCategoryId())
                .orElseThrow(() -> new BusinessException("Invalid category id."));

        store.setCategory(category);

        return repository.save(store);
    }

    @Override
    public Store get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));
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
        Optional<Store> store = repository.findById(id);

        if (store.isPresent()) {
            store.get().setName(dto.getName() != null ? dto.getName() : store.get().getName());

            if (dto.getAccountId() != null) {
                Account account = accountRepository
                        .findById(dto.getAccountId())
                        .orElseThrow(() -> new BusinessException("Invalid account id."));
                store.get().setAccount(account);
            }

            if (dto.getCategoryId() != null) {
                Category category = categoryRepository
                        .findById(dto.getCategoryId())
                        .orElseThrow(() -> new BusinessException("Invalid category id."));
                store.get().setCategory(category);
            }

            return repository.save(store.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found");
    }

}
