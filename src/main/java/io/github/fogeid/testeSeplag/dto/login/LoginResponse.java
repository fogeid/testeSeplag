package io.github.fogeid.testeSeplag.dto.login;

import java.util.Map;

public class LoginResponse {
    private Map<String, String> tokens;

    public LoginResponse(Map<String, String> tokens) {
        this.tokens = tokens;
    }

    public Map<String, String> getTokens() {
        return tokens;
    }

    public void setTokens(Map<String, String> tokens) {
        this.tokens = tokens;
    }
}