/*
 * package com.example.e_commerce.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.security.authentication.AuthenticationManager;
 * import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken;
 * import org.springframework.security.core.Authentication;
 * import org.springframework.security.core.context.SecurityContextHolder;
 * import org.springframework.web.bind.annotation.CrossOrigin;
 * import org.springframework.web.bind.annotation.PostMapping;
 * import org.springframework.web.bind.annotation.RequestBody;
 * import org.springframework.web.bind.annotation.RequestMapping;
 * import org.springframework.web.bind.annotation.RestController;
 * 
 * import com.example.e_commerce.entities.User;
 * import com.example.e_commerce.repositories.UserRepository;
 * import com.example.e_commerce.security.JwtUtils;
 * 
 * @RestController
 * 
 * @CrossOrigin(origins = "*")
 * 
 * @RequestMapping("/auth")
 * public class AuthController {
 * 
 * @Autowired
 * private UserRepository userRepository;
 * 
 * @Autowired
 * private AuthenticationManager authenticationManager;
 * 
 * @Autowired
 * private JwtUtils jwtUtils;
 * 
 * @PostMapping("/login")
 * public String login(@RequestBody User user) {
 * Authentication authentication = authenticationManager
 * .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
 * user.getPassword()));
 * 
 * SecurityContextHolder.getContext().setAuthentication(authentication);
 * 
 * String jwtToken = jwtUtils.generateJwtToken(authentication);
 * 
 * return jwtToken;
 * }
 * }
 */
