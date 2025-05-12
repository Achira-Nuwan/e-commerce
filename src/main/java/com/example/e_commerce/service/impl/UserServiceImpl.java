package com.example.e_commerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.e_commerce.entities.User;
import com.example.e_commerce.repositories.UserRepository;
import com.example.e_commerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        // user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode the
        // password make it not human readable
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(String email, User user) {
        User updateUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setPhoneNumber(user.getPhoneNumber());
        updateUser.setCompanyName(user.getCompanyName());
        updateUser.setAddress1(user.getAddress1());
        updateUser.setAddress2(user.getAddress2());
        updateUser.setCity(user.getCity());
        updateUser.setState(user.getState());
        updateUser.setCountry(user.getCountry());
        updateUser.setZipCode(user.getZipCode());
        updateUser.setNote(user.getNote());
        updateUser.setRole(user.getRole());

        return userRepository.save(updateUser);
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public User updatePassword(String email, String password) {
        User exisitinguser = userRepository.findByEmail(email).orElse(null);
        if (exisitinguser != null) {
            exisitinguser.setPassword(password);
            return userRepository.save(exisitinguser);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
