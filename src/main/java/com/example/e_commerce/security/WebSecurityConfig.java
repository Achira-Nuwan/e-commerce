/*
 * package com.example.e_commerce.security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.context.annotation.Bean;
 * import org.springframework.context.annotation.Configuration;
 * import org.springframework.security.authentication.AuthenticationManager;
 * import
 * org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 * import
 * org.springframework.security.config.annotation.authentication.configuration.
 * AuthenticationConfiguration;
 * import org.springframework.security.config.annotation.method.configuration.
 * EnableMethodSecurity;
 * import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.http.SessionCreationPolicy;
 * import org.springframework.security.core.userdetails.UserDetailsService;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.security.crypto.password.PasswordEncoder;
 * import org.springframework.security.web.SecurityFilterChain;
 * 
 * @Configuration
 * 
 * @EnableMethodSecurity
 * public class WebSecurityConfig {
 * 
 * @Autowired
 * private UserDetailsServiceImpl userDetailsService;
 * 
 * @Bean
 * public AuthTokenFilter authenticationJwTokenFilter() {
 * return new AuthTokenFilter();
 * }
 * 
 * @Bean
 * public PasswordEncoder passwordEncoder() {
 * return new BCryptPasswordEncoder();
 * }
 * 
 * @Bean
 * public UserDetailsService userDetailsService() {
 * return userDetailsService;
 * }
 * 
 * @Bean
 * public DaoAuthenticationProvider authenticationProvider() {
 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
 * 
 * authProvider.setUserDetailsService(userDetailsService);
 * authProvider.setPasswordEncoder(passwordEncoder());
 * 
 * return authProvider;
 * }
 * 
 * @Bean
 * public AuthenticationManager
 * authenticationManager(AuthenticationConfiguration authConfig) throws
 * Exception {
 * return authConfig.getAuthenticationManager();
 * }
 * 
 * @Bean
 * public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
 * Exception {
 * http.csrf(csrf -> csrf.disable())
 * .sessionManagement(session ->
 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
 * .authorizeHttpRequests(
 * auth -> auth
 * .requestMatchers(
 * "/auth/**",
 * "/products/**",
 * "/categories/**",
 * "/api/cart/**",
 * "/orders/**",
 * "/user/**")
 * .permitAll() // Allowing auth/login auth/register paths
 * .anyRequest().authenticated()); // User needs to login to access everything
 * else
 * 
 * http.authenticationProvider(authenticationProvider());
 * 
 * // Add filter
 * // http.addFilterBefore(authenticationJwTokenFilter(),
 * // UsernamePasswordAuthenticationFilter.class);
 * 
 * return http.build();
 * }
 * }
 */
