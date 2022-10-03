package com.erisvan.where.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erisvan.where.model.Calling;

@Service
public interface CallingService {

    public List<Calling> getAllCallings();

    public void createCalling(Calling calling);

    public void deleteCalling(Calling calling);

    public Calling getCallingById(Integer id);

}
