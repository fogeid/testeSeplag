package io.github.fogeid.testeSeplag.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestão de Servidores - SEPLAG")
                        .version("1.0.5")
                        .description("API para gerenciamento de servidores efetivos e temporários, incluindo consulta de endereços funcionais.")
                        .contact(new Contact()
                                .name("Diego Batista")
                                .email("batista.diego@protonmail.com")
                                .url("https://fogeid.github.io")));
    }
}
