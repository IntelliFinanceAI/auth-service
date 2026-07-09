package com.intellifinance.authservice.controller;

import com.intellifinance.authservice.dto.request.LoginRequest;
import com.intellifinance.authservice.dto.request.RegisterRequest;
import com.intellifinance.authservice.dto.response.ApiResponse;
import com.intellifinance.authservice.dto.response.LoginResponse;
import com.intellifinance.authservice.dto.response.RegisterResponse;
import com.intellifinance.authservice.service.AuthService;
import com.intellifinance.authservice.util.ApiResponseUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.intellifinance.authservice.constants.AppConstants.LOGIN_SUCCESSFUL;
import static com.intellifinance.authservice.constants.AppConstants.USER_REGISTERED_SUCCESSFULLY;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseUtils.success(USER_REGISTERED_SUCCESSFULLY, response)
        );
    }

    // login user....
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> loginUser(@Valid @RequestBody LoginRequest request) {
       LoginResponse response = authService.loginUser(request);
        return ResponseEntity
                .ok(ApiResponseUtils.success(LOGIN_SUCCESSFUL, response));
    }
}
