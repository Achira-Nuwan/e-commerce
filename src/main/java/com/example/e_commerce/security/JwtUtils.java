/*
 * package com.example.e_commerce.security;
 * 
 * import java.security.Key;
 * import java.util.Date;
 * 
 * import org.springframework.beans.factory.annotation.Value;
 * import org.springframework.context.annotation.Configuration;
 * import org.springframework.security.core.Authentication;
 * import org.springframework.security.core.userdetails.UserDetails;
 * 
 * import io.jsonwebtoken.Jwts;
 * import io.jsonwebtoken.SignatureAlgorithm;
 * import io.jsonwebtoken.io.Decoders;
 * import io.jsonwebtoken.security.Keys;
 * 
 * @Configuration
 * public class JwtUtils {
 * 
 * @Value("${app.secret}")
 * private String secret;
 * 
 * private Key key() {
 * return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)); // Generate key
 * with secret
 * }
 * 
 * // Method for generate a token
 * public String generateJwtToken(Authentication authentication) {
 * 
 * UserDetails user = (UserDetails) authentication.getPrincipal();
 * 
 * // generate JWT Token from user details above
 * return Jwts.builder()
 * .setSubject(user.getUsername())
 * .setIssuedAt(new Date())
 * .setExpiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 24)) //
 * Expire in 24 hours
 * .signWith(key(), SignatureAlgorithm.HS256)
 * .compact();
 * }
 * 
 * // Method for validate a token
 * public boolean validateJwtToken(String token) {
 * try {
 * Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
 * return true;
 * } catch (Exception e) {
 * e.printStackTrace();
 * return false;
 * }
 * }
 * 
 * // Extract username from token
 * public String extractUsernameFromToken(String token) {
 * return
 * Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).
 * getBody().getSubject(); // Get
 * // username
 * }
 * }
 */
