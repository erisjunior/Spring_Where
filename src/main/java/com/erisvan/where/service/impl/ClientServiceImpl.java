package com.erisvan.where.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.erisvan.where.exception.BusinessException;
import com.erisvan.where.model.Account;
import com.erisvan.where.model.Client;
import com.erisvan.where.repository.AccountRepository;
import com.erisvan.where.repository.ClientRepository;
import com.erisvan.where.rest.dto.ClientDTO;
import com.erisvan.where.service.ClientService;

@Component
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository repository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Client save(ClientDTO dto) {
        Client client = new Client();
        client.setName(dto.getName());

        Account account = accountRepository
                .findById(dto.getAccountId())
                .orElseThrow(() -> new BusinessException("Invalid account id."));

        client.setAccount(account);

        return repository.save(client);
    }

    @Override
    public Client get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @Override
    public List<Client> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Client update(Integer id, ClientDTO dto) {
        Optional<Client> client = repository.findById(id);

        if (client.isPresent()) {
            client.get().setName(dto.getName() != null ? dto.getName() : client.get().getName());

            if (dto.getAccountId() != null) {
                Account account = accountRepository
                        .findById(dto.getAccountId())
                        .orElseThrow(() -> new BusinessException("Invalid account id."));
                client.get().setAccount(account);
            }

            return repository.save(client.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
    }

}
