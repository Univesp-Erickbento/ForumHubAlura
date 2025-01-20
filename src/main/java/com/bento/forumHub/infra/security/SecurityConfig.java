package com.bento.forumHub.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Aqui você pode buscar o usuário no banco de dados
            if ("user".equals(username)) {
                return User.withUsername("user")
                        .password(passwordEncoder().encode("password"))
                        .roles("USER")
                        .build();
            } else {
                throw new UsernameNotFoundException("Usuário não encontrado");
            }
        };
    }

    // Definir o AuthenticationManager para ser injetado em outros lugares
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configuração do HttpSecurity para definir permissões de acesso
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/auth/**").permitAll()  // Permite acesso sem autenticação
                .anyRequest().authenticated() // Exige autenticação para qualquer outra requisição
                .and();
    }
}
