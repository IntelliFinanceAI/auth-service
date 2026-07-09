package com.intellifinance.authservice.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        String tokenType
) {
}
