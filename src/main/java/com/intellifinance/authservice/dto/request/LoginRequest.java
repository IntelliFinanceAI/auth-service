package com.intellifinance.authservice.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
