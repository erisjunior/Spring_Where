package com.erisvan.where.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.erisvan.where.model.Account;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.key}")
    private String secretKey;

    public String generateToken(Account account) {
        long expString = Long.valueOf(expiration);
        LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(expString);
        Instant instant = expirationDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        return Jwts
                .builder()
                .setSubject(account.getLogin())
                .setExpiration(data)
                .signWith(key)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenIsValid(String token) {
        try {
            Claims claims = (Claims) getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime data = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        } catch (Exception e) {
            return false;
        }
    }

    public String getAccountLogin(String token) throws ExpiredJwtException {
        return (String) (getClaims(token)).getSubject();
    }

    public static void main(String[] args) {

        // ConfigurableApplicationContext contexto =
        // SpringApplication.run(WhereApplication.class);
        // JwtService service = contexto.getBean(JwtService.class);
        // Usuario usuario = Usuario.builder().login("fulano").build();
        // System.out.println("Gerando token");
        // String token = service.generateToken(usuario);
        // System.out.println(token);

        // System.out.println("Verifica se o token é válido");
        // boolean istokenIsValid = service.tokenIsValid(token);
        // System.out.println("O token é válido? " + istokenIsValid);

        // System.out.println("Obtenha o login do usuário");
        // String login = service.obterLoginUsuario(token);
        // System.out.println(login);

    }
}
