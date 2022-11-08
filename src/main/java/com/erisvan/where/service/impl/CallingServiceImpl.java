package com.erisvan.where.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.erisvan.where.exception.BusinessException;
import com.erisvan.where.model.Calling;
import com.erisvan.where.model.Category;
import com.erisvan.where.model.Client;
import com.erisvan.where.repository.CallingRepository;
import com.erisvan.where.repository.CategoryRepository;
import com.erisvan.where.repository.ClientRepository;
import com.erisvan.where.rest.dto.CallingDTO;
import com.erisvan.where.service.CallingService;

@Component
public class CallingServiceImpl implements CallingService {

    @Autowired
    CallingRepository repository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Calling save(CallingDTO dto) {
        Calling calling = new Calling();
        calling.setTitle(dto.getTitle());
        calling.setDescription(dto.getDescription());

        Client client = clientRepository
                .findById(dto.getClientId())
                .orElseThrow(() -> new BusinessException("Invalid client id."));

        calling.setClient(client);

        Category category = categoryRepository
                .findById(dto.getCategoryId())
                .orElseThrow(() -> new BusinessException("Invalid category id."));

        calling.setCategory(category);

        return repository.save(calling);
    }

    @Override
    public Calling get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Calling not found"));
    }

    @Override
    public List<Calling> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Calling> getByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException("Invalid category id."));

        return repository.findAllByCategory(category);
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

            if (dto.getClientId() != null) {
                Client client = clientRepository
                        .findById(dto.getClientId())
                        .orElseThrow(() -> new BusinessException("Invalid client id."));
                calling.get().setClient(client);
            }
            if (dto.getCategoryId() != null) {
                Category category = categoryRepository
                        .findById(dto.getCategoryId())
                        .orElseThrow(() -> new BusinessException("Invalid category id."));
                calling.get().setCategory(category);
            }
            return repository.save(calling.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Calling not found");
    }

}
