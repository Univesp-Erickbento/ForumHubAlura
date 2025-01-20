package com.bento.forumHub.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {

    private static final String SECRET_KEY = "your_secret_key"; // Use um segredo mais seguro para produção
    private static final long EXPIRATION_TIME = 86400000L; // 1 dia em milissegundos

    // Método para gerar o token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Método para extrair o nome de usuário do token
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()  // Usando parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)  // Parse do token com a chave secreta
                .getBody();

        return claims.getSubject();  // Retorna o username extraído do corpo do token
    }

    // Método para verificar se o token expirou
    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser()   // Usando parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)  // Parse do token com a chave secreta
                .getBody();

        Date expirationDate = claims.getExpiration();  // Obtém a data de expiração do token
        return expirationDate.before(new Date());  // Retorna se o token expirou
    }
}
