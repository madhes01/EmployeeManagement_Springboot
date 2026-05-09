package com.madhes.EmployeeManagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.Date;

@Service
public class JwtService {

        // Injected from application.properties → jwt.secret
        @Value("${jwt.secret}")
        private String secretKey;

        @Value("${jwt.expiration-ms}")
        private long expirationMs;

        /**
         * Builds an HMAC-SHA256 signing key from the secret string.
         * Keys.hmacShaKeyFor() requires at least 256 bits (32 bytes).
         */
        private Key getSignKey() {
                return Keys.hmacShaKeyFor(secretKey.getBytes());
        }

        public String generateToken(String username) {
                return Jwts.builder()
                                .setSubject(username)
                                .setIssuedAt(new Date())
                                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                                .compact();
        }

        public String extractUsername(String token) {
                return extractClaims(token).getSubject();
        }

        /**
         * Parses and returns all claims from the JWT.
         * Throws JwtException (SignatureException, ExpiredJwtException, etc.)
         * if the token is tampered with or expired.
         */
        private Claims extractClaims(String token) {
                return Jwts.parserBuilder()
                                .setSigningKey(getSignKey())
                                .build()
                                .parseClaimsJws(token)
                                .getBody();
        }

        /**
         * Validates token:
         * 1. Username in token must match the provided username
         * 2. Token must not be expired
         */
        public boolean isTokenValid(String token, String username) {
                String extractedUsername = extractUsername(token);
                return extractedUsername.equals(username) && !isTokenExpired(token);
        }

        private boolean isTokenExpired(String token) {
                return extractClaims(token)
                                .getExpiration()
                                .before(new Date());
        }
}