package com.mycompany.online_shop_demo_backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final String jwtSecret;
    private final long jwtValidity;

    public JwtProvider(@Value("${application.jwtSecret}") String jwtSecret,
                       @Value("${application.jwtValidity}") long jwtValidity) {
        this.jwtSecret = jwtSecret;
        this.jwtValidity = jwtValidity;
    }

    public String generateToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtValidity);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public long getJwtValidity() {
        return jwtValidity;
    }

    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
