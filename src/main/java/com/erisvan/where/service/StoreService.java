package com.erisvan.where.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erisvan.where.model.Store;
import com.erisvan.where.rest.dto.StoreDTO;

@Service
public interface StoreService {
    Store save(StoreDTO dto);

    Store get(Integer id);

    List<Store> getAll();

    void delete(Integer id);

    Store update(Integer id, StoreDTO dto);

}
