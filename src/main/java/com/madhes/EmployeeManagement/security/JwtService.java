package com.madhes.EmployeeManagement.security;

import io.jsonwebtoken.Claims; import io.jsonwebtoken.Jwts; import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.Date;

@Service 
public class JwtService {

private static final String SECRET_KEY =
        "mysecretkeymysecretkeymysecretkey12345";

private Key getSignKey() {

    return Keys.hmacShaKeyFor(
            SECRET_KEY.getBytes());
}

public String generateToken(String username) {

    return Jwts.builder()

            .setSubject(username)

            .setIssuedAt(new Date())

            .setExpiration(
                    new Date(System.currentTimeMillis()
                            + 1000 * 60 * 60 * 24))

            .signWith(
                    getSignKey(),
                    SignatureAlgorithm.HS256)

            .compact();
}

public String extractUsername(String token) {

    return extractClaims(token).getSubject();
}

private Claims extractClaims(String token) {

    return Jwts.parserBuilder()

            .setSigningKey(getSignKey())

            .build()

            .parseClaimsJws(token)

            .getBody();
}

public boolean isTokenValid(
        String token,
        String username) {

    String extractedUsername =
            extractUsername(token);

    return extractedUsername.equals(username)
            && !isTokenExpired(token);
}

private boolean isTokenExpired(String token) {

    return extractClaims(token)
            .getExpiration()
            .before(new Date());
}
}