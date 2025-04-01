package io.github.fogeid.testeSeplag.controllers;

import io.github.fogeid.testeSeplag.dto.login.LoginRequest;
import io.github.fogeid.testeSeplag.dto.login.LoginResponse;
import io.github.fogeid.testeSeplag.dto.login.RefreshTokenRequest;
import io.github.fogeid.testeSeplag.services.JwtService;
import io.github.fogeid.testeSeplag.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Recebendo requisição de login para o usuário: {}", loginRequest.getUsername());

        AuthenticationManager authManager = authenticationManager;
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        UserDetails userDetails = usuarioService.loadUserByUsername(loginRequest.getUsername());
        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        logger.info("Tokens gerados com sucesso para o usuário: {}", loginRequest.getUsername());
        return ResponseEntity.ok(new LoginResponse(tokens));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        logger.info("Recebendo requisição para renovar access token");

        if (refreshTokenRequest == null || refreshTokenRequest.getRefreshToken() == null || refreshTokenRequest.getRefreshToken().trim().isEmpty()) {
            logger.error("Refresh token não foi fornecido ou está vazio");
            throw new IllegalArgumentException("Refresh token é obrigatório");
        }

        String refreshToken = refreshTokenRequest.getRefreshToken();
        logger.info("Refresh token recebido: {}", refreshToken);

        boolean isValid = jwtService.validateRefreshToken(refreshToken);
        logger.info("Resultado da validação do refresh token: {}", isValid);
        if (!isValid) {
            logger.error("Refresh token inválido ou expirado: {}", refreshToken);
            throw new RuntimeException("Refresh token inválido ou expirado");
        }

        String username = jwtService.extractUsername(refreshToken);
        logger.info("Username extraído do refresh token: {}", username);

        UserDetails userDetails = usuarioService.loadUserByUsername(username);

        String newAccessToken = jwtService.generateToken(userDetails);
        logger.info("Novo access token gerado para o usuário: {}", username);

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }
}