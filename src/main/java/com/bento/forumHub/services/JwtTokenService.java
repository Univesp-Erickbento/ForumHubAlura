package com.bento.forumHub.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration.hours:2}")
    private int expirationHours;

    /**
     * Gera um token JWT para o usuário com base no nome de usuário fornecido.
     *
     * @param username Nome de usuário para o qual o token será gerado.
     * @return Token JWT gerado.
     * @throws RuntimeException Se ocorrer um erro durante a criação do token.
     */
    public String gerarToken(String username) {
        validarSecret();
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ForumHub") // Identifica o emissor do token
                    .withSubject(username)  // Define o usuário como o sujeito do token
                    .withExpiresAt(dataExpiracao()) // Define a data de expiração
                    .sign(algoritmo); // Assina o token com o algoritmo configurado
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    /**
     * Obtém o subject (usuário) de um token JWT válido.
     *
     * @param tokenJWT Token JWT que será verificado.
     * @return O subject do token.
     * @throws RuntimeException Se o token for inválido ou expirado.
     */
    public String getSubject(String tokenJWT) {
        validarSecret();
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("ForumHub") // Garante que o emissor corresponde ao esperado
                    .build()
                    .verify(tokenJWT) // Verifica o token
                    .getSubject(); // Obtém o subject do token
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!", exception);
        }
    }

    /**
     * Calcula a data de expiração com base no tempo atual e no valor configurado de horas.
     *
     * @return Data e hora de expiração do token.
     */
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(expirationHours).toInstant(ZoneOffset.of("-03:00"));
    }

    /**
     * Valida se a chave secreta está configurada corretamente.
     *
     * @throws IllegalStateException Se a chave secreta não estiver configurada.
     */
    private void validarSecret() {
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException("A chave secreta do JWT não foi configurada. Verifique o arquivo application.properties.");
        }
    }
}
