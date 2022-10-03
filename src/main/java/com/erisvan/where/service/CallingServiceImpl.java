package com.erisvan.where.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erisvan.where.model.Calling;
import com.erisvan.where.repository.CallingRepository;

@Component
public class CallingServiceImpl implements CallingService {

    @Autowired
    CallingRepository callingRepository;

    @Override
    public List<Calling> getAllCallings() {
        return callingRepository.findAll();
    }

    @Override
    public void createCalling(Calling calling) {
        callingRepository.save(calling);
    }

    @Override
    public void deleteCalling(Calling calling) {
        callingRepository.delete(calling);
    }

    @Override
    public Calling getCallingById(Integer id) {
        return callingRepository.findById(id).map(calling -> {
            return calling;
        }).orElseThrow(() -> null);
    }

}
