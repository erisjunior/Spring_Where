package com.erisvan.where.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erisvan.where.model.User;

@Service
public interface UserService {

    public List<User> getAllUsers();

    public void createUser(User user);

    public void deleteUser(User user);

    public User getUserById(Integer id);

}
