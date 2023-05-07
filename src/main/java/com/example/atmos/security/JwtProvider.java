package com.example.atmos.security;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


@Component
public class JwtProvider {
//    @Value("${jwt.secretKey}")
//    private String secretKey;
//
//    @Value("${jwt.expireDate}")
//    private int expireDate;
//
//    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
//
//    public Key getSecretKey() {
//        final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
//        return new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public boolean hasClaim(String token, String claimName) {
//        final Claims claims = extractAllClaims(token);
//        return claims.get(claimName) != null;
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
//        return createToken(userDetails, claims);
//
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(userDetails, claims);
//    }
//
//    public String createToken(UserDetails userDetails, Map<String, Object> claims) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(SIGNATURE_ALGORITHM, getSecretKey())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(expireDate))) //30 kun amal qiladi
//                .setSubject(userDetails.getUsername())
//                .compact();
//    }
//
//    public Boolean isTokenValid(String token, UserDetails userDetails) {
//        String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
@Value("${jwt.secretKey}")
private String secret;

    @Value("${jwt.expireDate}")
    private long time;

    public String generateToken(String username) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secret)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + time * 30)) //30 kun amal qiladi
                .setSubject(username)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean expireToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .after(new Date());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException s) {
            System.err.println("Invalid JWT");
        } catch (MalformedJwtException s) {
            System.err.println("Malformed ");
        } catch (ExpiredJwtException e) {
            System.err.println("Expired");
        } catch (UnsupportedJwtException e) {
            System.err.println("Unsupported");
        } catch (IllegalArgumentException e) {
            System.err.println("Not empty");
        }
        return false;
    }
}
