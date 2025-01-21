package com.bento.forumHub.infra.security;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("API do Fórum Hub")
                        .version("1.0")
                        .description("Documentação da API para o Fórum Hub"));
    }
}
