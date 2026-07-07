package com.intellifinance.authservice.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponse(
        boolean success,
        String message,
        Map<String, String> errors,
        LocalDateTime localDateTime
) {
}
