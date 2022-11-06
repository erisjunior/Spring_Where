package com.erisvan.where.service;

import java.util.List;
import java.util.Optional;

import com.erisvan.where.model.Store;
import com.erisvan.where.rest.dto.StoreDTO;

public interface StoreService {
    Store save(StoreDTO dto);

    Optional<Store> get(Integer id);

    List<Store> getAll();

    void delete(Integer id);

    Store update(Integer id, StoreDTO dto);

}
