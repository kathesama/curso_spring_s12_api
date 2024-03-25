package com.kahesama.demo.curso_spring_s12_api.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/users/v1/api").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                        // .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.GET, "/api/products", "/api/products/{id}").hasAnyRole("ADMIN", "USER")
                        // .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.PUT, "/api/products/{id}").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.DELETE, "/api/products/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated())
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                .addFilter(new JwtValidationFilter(authenticationManager()))
//                .csrf(config -> config.disable())
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
