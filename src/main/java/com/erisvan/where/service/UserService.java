package com.erisvan.where.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erisvan.where.model.Client;

@Service
public interface UserService {

    public List<Client> getAllUsers();

    public void createUser(Client user);

    public void deleteUser(Client user);

    public Client getUserById(Integer id);

}
