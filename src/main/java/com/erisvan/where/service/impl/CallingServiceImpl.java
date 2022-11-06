package com.erisvan.where.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.erisvan.where.model.Calling;
import com.erisvan.where.repository.CallingRepository;
import com.erisvan.where.rest.dto.CallingDTO;
import com.erisvan.where.service.CallingService;

@Component
public class CallingServiceImpl implements CallingService {

    @Autowired
    CallingRepository repository;

    @Override
    public Calling save(CallingDTO dto) {
        Calling calling = new Calling();
        calling.setTitle(dto.getTitle());
        calling.setDescription(dto.getDescription());
        calling.setClient(dto.getClient());
        calling.setStores(dto.getStores());
        return repository.save(calling);
    }

    @Override
    public Optional<Calling> get(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Calling> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Calling update(Integer id, CallingDTO dto) {
        Optional<Calling> calling = repository.findById(id);

        if (calling.isPresent()) {
            calling.get().setTitle(dto.getTitle() != null ? dto.getTitle() : calling.get().getTitle());
            calling.get().setDescription(
                    dto.getDescription() != null ? dto.getDescription() : calling.get().getDescription());
            calling.get().setClient(dto.getClient() != null ? dto.getClient() : calling.get().getClient());
            calling.get().setStores(dto.getStores() != null ? dto.getStores() : calling.get().getStores());
            return repository.save(calling.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Calling not found");
    }

}
