package com.erisvan.where.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erisvan.where.model.Client;
import com.erisvan.where.repository.ClientRepository;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    ClientRepository userRepository;

    @Override
    public List<Client> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(Client user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Client user) {
        userRepository.delete(user);
    }

    @Override
    public Client getUserById(Integer id) {
        return userRepository.findById(id).map(user -> {
            return user;
        }).orElseThrow(() -> null);
    }

}
