package com.bento.forumHub.controllers;

import com.bento.forumHub.domain.request.LoginRequest;
import com.bento.forumHub.services.JwtTokenService;
import com.bento.forumHub.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.BadCredentialsException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        try {
            // Realiza a autenticação
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // Recupera os detalhes do usuário autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Gera e retorna o token JWT
            return jwtTokenService.gerarToken(userDetails.getUsername());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials", e);
        }
    }
}
