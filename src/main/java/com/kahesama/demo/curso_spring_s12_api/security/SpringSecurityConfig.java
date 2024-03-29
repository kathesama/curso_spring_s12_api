package com.kahesama.demo.curso_spring_s12_api.security;


import com.kahesama.demo.curso_spring_s12_api.security.filter.JwtAuthenticationFilter;
import com.kahesama.demo.curso_spring_s12_api.security.filter.JwtValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final MessageSource messageSource;
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests((authorize) -> authorize

                    .requestMatchers(HttpMethod.GET, "/users/v1/api", "/users/v1/api/{id}").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users/v1/api/register").permitAll() //cualquier puede usar este endpoint
//                    .requestMatchers(HttpMethod.POST, "/users/v1/api").hasRole(ADMIN.getSimpleName()) // create solo para admins
//                    .requestMatchers(HttpMethod.DELETE, "/users/v1/api").hasRole(ADMIN.getSimpleName()) // eliminar solo para admins

//                    .requestMatchers(HttpMethod.GET, "/products/v1/api", "/products/v1/api/{id}").hasAnyRole(ADMIN.getSimpleName(), USER.getSimpleName())
//                    .requestMatchers(HttpMethod.POST, "/products/v1/api").hasRole(ADMIN.getSimpleName())
//                    .requestMatchers(HttpMethod.PUT, "/products/v1/api/{id}").hasRole(ADMIN.getSimpleName())
//                    .requestMatchers(HttpMethod.DELETE, "/products/v1/api/{id}").hasRole(ADMIN.getSimpleName())
                    .anyRequest().authenticated()
                )
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), messageSource))
                .addFilter(new JwtValidationFilter(authenticationManager(), messageSource))
                .csrf(AbstractHttpConfigurer::disable) //evitar vulnerabilidades
                .httpBasic(withDefaults())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }
}
