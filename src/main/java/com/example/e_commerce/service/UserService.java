package com.example.e_commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.User;

@Service
public interface UserService {
    List<User> getAllUsers();

    User createUser(User user);

    User getUserByEmail(String email);

    User updateUser(String email, User user);

    User updatePassword(String email, String password);

    void deleteUserByEmail(String email);
}
