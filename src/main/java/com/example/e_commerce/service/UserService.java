package com.example.e_commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.User;

@Service
public interface UserService {
    List<User> getAllUsers();

    User createUser(User user);

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUserById(Long id); 
}
