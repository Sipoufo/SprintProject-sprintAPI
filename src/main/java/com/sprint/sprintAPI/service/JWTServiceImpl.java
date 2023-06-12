package com.sprint.sprintAPI.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    private final static String SECRET_KEY = "2F423F4528482B4D6251655468576D5A7134743777397A24432646294A404E63";

    // Function use to extract email to token
    public String extractUserEmail (String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract one claims
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims in the token
    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSigninKey())
                .parseClaimsJws(token)
                .getBody();
    }

    // Generate token for user detail only
    public  String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Generate token
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 24))
                .signWith(SignatureAlgorithm.HS256, getSigninKey())
                .compact();
    }

    // Verify token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userEmail = extractUserEmail(token);
        System.out.println("userEmail.equals(userDetails.getPassword()) => " + userEmail.equals(userDetails.getPassword()));
        System.out.println("!isTokenExpired(token) => " + !isTokenExpired(token));
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Check if token expired
    public boolean isTokenExpired(String token) {
        System.out.println("new Date() => " + new Date());
        System.out.println("extractExpiration(token) => " + extractExpiration(token));
        return extractExpiration(token).before(new Date());
    }

    // Get expiration for this token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
