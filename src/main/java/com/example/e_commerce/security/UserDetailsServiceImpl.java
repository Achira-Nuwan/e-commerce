/*
 * package com.example.e_commerce.security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.security.core.userdetails.UserDetails;
 * import org.springframework.security.core.userdetails.UserDetailsService;
 * import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.example.e_commerce.entities.User;
 * import com.example.e_commerce.repositories.UserRepository;
 * 
 * @Service
 * public class UserDetailsServiceImpl implements UserDetailsService {
 * 
 * @Autowired
 * private UserRepository userRepository;
 * 
 * @Override
 * public UserDetails loadUserByUsername(String username) {
 * User user = userRepository.findByUsername(username).orElse(null);
 * 
 * if (user == null) {
 * throw new UsernameNotFoundException("User name not found");
 * } else {
 * return org.springframework.security.core.userdetails.User.builder()
 * .username(user.getUsername())
 * .password(user.getPassword())
 * .build();
 * }
 * }
 * }
 */
