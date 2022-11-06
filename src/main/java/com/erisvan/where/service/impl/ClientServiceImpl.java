package com.erisvan.where.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.erisvan.where.model.Client;
import com.erisvan.where.repository.ClientRepository;
import com.erisvan.where.rest.dto.ClientDTO;
import com.erisvan.where.service.ClientService;

@Component
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository repository;

    @Override
    public Client save(ClientDTO dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setAccount(dto.getAccount());
        client.setCallings(dto.getCallings());
        return repository.save(client);
    }

    @Override
    public Optional<Client> get(Integer id) {
        return repository.findById(id);
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
            client.get().setAccount(dto.getAccount() != null ? dto.getAccount() : client.get().getAccount());
            client.get().setCallings(dto.getCallings() != null ? dto.getCallings() : client.get().getCallings());
            return repository.save(client.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
    }

}
