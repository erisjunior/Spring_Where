package com.erisvan.where.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erisvan.where.model.Store;
import com.erisvan.where.repository.StoreRepository;

@Component
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public void createStore(Store store) {
        storeRepository.save(store);
    }

    @Override
    public void deleteStore(Store store) {
        storeRepository.delete(store);
    }

    @Override
    public Store getStoreById(Integer id) {
        return storeRepository.findById(id).map(store -> {
            return store;
        }).orElseThrow(() -> null);
    }

}
