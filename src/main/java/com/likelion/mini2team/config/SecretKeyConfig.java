package com.likelion.mini2team.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class SecretKeyConfig {
    @Value("${jwt.token.secret}")
    private String key;

    @Bean
    public SecretKey secretKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }
}
