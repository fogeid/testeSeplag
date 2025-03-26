package io.github.fogeid.testeSeplag.config;

import io.github.fogeid.testeSeplag.entities.Usuario;
import io.github.fogeid.testeSeplag.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            log.info("Inicializando dados de teste...");

            // Usuário com role USER
            if (usuarioRepository.findByUsername("user").isEmpty()) {
                Usuario user = new Usuario();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRoles("ROLE_USER");
                usuarioRepository.save(user);
                log.info("Usuário 'user' criado com sucesso. Senha criptografada: {}", user.getPassword());
            } else {
                log.info("Usuário 'user' já existe. Pulando criação.");
            }

            // Usuário com role ADMIN
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles("ROLE_ADMIN");
                usuarioRepository.save(admin);
                log.info("Usuário 'admin' criado com sucesso. Senha criptografada: {}", admin.getPassword());
            } else {
                log.info("Usuário 'admin' já existe. Pulando criação.");
            }

            log.info("Dados de teste inicializados com sucesso.");
        };
    }
}