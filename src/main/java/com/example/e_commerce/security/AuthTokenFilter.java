/*
 * package com.example.e_commerce.security;
 * 
 * import java.io.IOException;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken;
 * import org.springframework.security.core.context.SecurityContextHolder;
 * import
 * org.springframework.security.web.authentication.WebAuthenticationDetails;
 * import org.springframework.util.StringUtils;
 * import org.springframework.web.filter.OncePerRequestFilter;
 * 
 * import jakarta.servlet.FilterChain;
 * import jakarta.servlet.ServletException;
 * import jakarta.servlet.http.HttpServletRequest;
 * import jakarta.servlet.http.HttpServletResponse;
 * 
 * public class AuthTokenFilter extends OncePerRequestFilter {
 * 
 * @Autowired
 * private JwtUtils jwtUtils;
 * 
 * private String parseJwtFromHeader(HttpServletRequest request) {
 * String authHeader = request.getHeader("Authorization");
 * 
 * if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
 * return authHeader.substring(7); // Remove first 7 characters from token which
 * is Bearer
 * } else {
 * return null;
 * }
 * }
 * 
 * @Override
 * protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain)
 * throws IOException, ServletException {
 * 
 * try {
 * // Check JWT token in the Request and Validate
 * String jwtToken = parseJwtFromHeader(request);
 * 
 * if (jwtToken != null && jwtUtils.validateJwtToken(jwtToken)) {
 * 
 * // extract username from token
 * String username = jwtUtils.extractUsernameFromToken(jwtToken);
 * 
 * // build authentication object
 * UsernamePasswordAuthenticationToken authentication = new
 * UsernamePasswordAuthenticationToken(username,
 * null,
 * null);
 * authentication.setDetails(new WebAuthenticationDetails(request));
 * 
 * SecurityContextHolder.getContext().setAuthentication(authentication);
 * }
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * 
 * filterChain.doFilter(request, response);
 * }
 * }
 */
