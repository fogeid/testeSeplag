package io.github.fogeid.testeSeplag.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Insira o token JWT no formato 'Bearer <token>'")))
                .info(new Info()
                        .title("API de Gestão de Servidores - SEPLAG")
                        .version("1.1.0")
                        .description("API para gerenciamento de servidores efetivos e temporários, incluindo consulta de endereços funcionais.")
                        .contact(new Contact()
                                .name("Diego Batista")
                                .email("batista.diego@protonmail.com")
                                .url("https://fogeid.github.io")));
    }
}
