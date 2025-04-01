package io.github.fogeid.testeSeplag.config;

import io.github.fogeid.testeSeplag.entities.Usuario;
import io.github.fogeid.testeSeplag.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;

@Configuration
public class DataInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        logger.info("Inicializando dados de teste...");

        // Usuário com role USER
        if (usuarioRepository.findByUsername("user").isEmpty()) {
            Usuario user = new Usuario();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles("ROLE_USER");
            usuarioRepository.save(user);
            logger.info("Usuário 'user' criado com sucesso.");
        } else {
            logger.info("Usuário 'user' já existe. Pulando criação.");
        }

        // Usuário com role ADMIN
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles("ROLE_ADMIN");
            usuarioRepository.save(admin);
            logger.info("Usuário 'admin' criado com sucesso.");
        } else {
            logger.info("Usuário 'admin' já existe. Pulando criação.");
        }

        logger.info("Dados de teste inicializados com sucesso.");
    }
}