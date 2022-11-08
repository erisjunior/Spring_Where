package com.erisvan.where.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erisvan.where.model.Client;
import com.erisvan.where.rest.dto.ClientDTO;

@Service
public interface ClientService {
    Client save(ClientDTO dto);

    Client get(Integer id);

    List<Client> getAll();

    void delete(Integer id);

    Client update(Integer id, ClientDTO dto);

}
