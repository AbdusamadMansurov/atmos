package com.example.weatherapi.config;

import com.example.weatherapi.model.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtUtil {
    @Value("${jwt.secretKey}")
    private String secret;
    @Value("${jwt.expireDate}")
    private String expirationTime;

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public String extractUsername(String authToken) {
        return getClaimsFromToken(authToken)
                .getSubject();
    }

    public Claims getClaimsFromToken(String authToken) {
        String key = Base64.getEncoder().encodeToString(secret.getBytes());
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(authToken)
                .getBody();
    }

    public boolean validateToken(String authToken) {
        return getClaimsFromToken(authToken)
                .getExpiration()
                .after(new Date());
    }

    public String generateToken(Users user) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", List.of(user.getRole()));

        long expirationSeconds = Long.parseLong(expirationTime);
        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + expirationSeconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }
}
