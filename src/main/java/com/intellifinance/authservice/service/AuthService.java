package com.intellifinance.authservice.service;

import com.intellifinance.authservice.dto.request.LoginRequest;
import com.intellifinance.authservice.dto.request.RegisterRequest;
import com.intellifinance.authservice.dto.response.LoginResponse;
import com.intellifinance.authservice.dto.response.RegisterResponse;

public interface AuthService {

    public RegisterResponse register(RegisterRequest request);

    public LoginResponse loginUser(LoginRequest request);
    
}
