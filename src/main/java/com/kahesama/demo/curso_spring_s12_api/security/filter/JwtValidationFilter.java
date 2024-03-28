package com.kahesama.demo.curso_spring_s12_api.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kahesama.demo.curso_spring_s12_api.security.SimpleGrantedAuthorityJsonCreator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static com.kahesama.demo.curso_spring_s12_api.security.TokenJwtConfig.*;

public class JwtValidationFilter extends BasicAuthenticationFilter {
    private final MessageSource messageSource;

    public JwtValidationFilter(AuthenticationManager authenticationManager, MessageSource messageSource) {
        super(authenticationManager);
        this.messageSource = messageSource;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(PREFIX_TOKEN, "");

        try{
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            Object authoritiesClaims = claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper()
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                            .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class)
            );

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }catch (Exception ex){
            Map<String, Object> body = new HashMap<>();

            body.put("code", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
            body.put("message", messageSource.getMessage("Error.login.invalidToken", null, Locale.getDefault()));
            body.put("details", List.of(ex.getMessage()));
            body.put("timestamp", String.valueOf(LocalDateTime.now()));

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);
        }
    }
}
