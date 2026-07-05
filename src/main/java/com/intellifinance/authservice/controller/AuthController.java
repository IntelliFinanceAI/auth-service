package com.intellifinance.authservice.controller;

import com.intellifinance.authservice.dto.request.RegisterRequest;
import com.intellifinance.authservice.dto.response.ApiResponse;
import com.intellifinance.authservice.dto.response.RegisterResponse;
import com.intellifinance.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        ApiResponse<RegisterResponse> apiResponse = new ApiResponse<>(
                true,
                "User registered successfully",
                response,
                java.time.LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
