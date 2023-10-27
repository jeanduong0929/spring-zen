package com.jean.zen.services;

import com.jean.zen.dtos.responses.Principal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  @Value("${jwt.secret}") private String secret;

  public String generateToken(Principal principal) {
    return Jwts.builder()
        .setIssuer("flitter")
        .setId(principal.getId())
        .setSubject(principal.getEmail())
        .claim("role", principal.getRole())
        .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public Principal parseToken(String token) {
    Claims claims =
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    return new Principal(claims.getId(), claims.getSubject(),
                         claims.get("role", String.class));
  }
}
