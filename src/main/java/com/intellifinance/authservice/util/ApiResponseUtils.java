package com.intellifinance.authservice.util;

import com.intellifinance.authservice.dto.response.ApiResponse;

public final class ApiResponseUtils {

    public static <T>ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, java.time.LocalDateTime.now());
    }

    public static <T>ApiResponse<T> errors(String message, T data) {
        return new ApiResponse<>(false, message, data, java.time.LocalDateTime.now());
    }

}
