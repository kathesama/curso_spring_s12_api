package com.kahesama.demo.curso_spring_s12_api.security;

import io.jsonwebtoken.Jwts;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;

@NoArgsConstructor
public class TokenJwtConfig {
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
}
