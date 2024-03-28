package com.kahesama.demo.curso_spring_s12_api.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kahesama.demo.curso_spring_s12_api.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static com.kahesama.demo.curso_spring_s12_api.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private MessageSource messageSource;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, MessageSource messageSource) {
        this.authenticationManager = authenticationManager;
        this.messageSource = messageSource;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = getUserFromRequest(request);
        if (user == null) {
            throw new BadCredentialsException("Unable to parse user from request");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        return authenticationManager.authenticate(authenticationToken);
    }

    private User getUserFromRequest(HttpServletRequest request) {
        try {
            return new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

        Claims claims = Jwts.claims()
                .add("authorities", new ObjectMapper().writeValueAsString(roles))
                .add("username", username)
                .build();

        String token = Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        List<Map<String, Object>> detalles = new ArrayList<>();
        detalles.add(Map.of("token", token));
        detalles.add(Map.of("username", username));

        Map<String, Object> body = new HashMap<>();
        body.put("code", String.valueOf(HttpStatus.OK.value()));
        body.put("message", String.format("Hola %s has iniciado sesión con éxito!", username));
        body.put("details", detalles);
        body.put("timestamp", String.valueOf(LocalDateTime.now()));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<>();

        body.put("code", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        body.put("message", messageSource.getMessage("Error.login.usernameOrPassword", null, Locale.getDefault()));
        body.put("details", List.of(failed.getMessage()));
        body.put("timestamp", String.valueOf(LocalDateTime.now()));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(CONTENT_TYPE);
    }
}
