package com.erisvan.where.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erisvan.where.model.Store;

@Service
public interface StoreService {

    public List<Store> getAllStores();

    public void createStore(Store store);

    public void deleteStore(Store store);

    public Store getStoreById(Integer id);

}
