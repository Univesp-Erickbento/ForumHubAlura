package com.bento.forumHub.controllers;

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
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtTokenService.generateToken(userDetails.getUsername());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials", e); // Captura a exceção de credenciais inválidas
        }
    }
}
