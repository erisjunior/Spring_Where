package com.erisvan.where.service;

import java.util.List;
import java.util.Optional;

import com.erisvan.where.model.Client;
import com.erisvan.where.rest.dto.ClientDTO;

public interface ClientService {
    Client save(ClientDTO dto);

    Optional<Client> get(Integer id);

    List<Client> getAll();

    void delete(Integer id);

    Client update(Integer id, ClientDTO dto);

}
