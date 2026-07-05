package com.intellifinance.authservice.service.impl;

import com.intellifinance.authservice.dto.request.RegisterRequest;
import com.intellifinance.authservice.dto.response.RegisterResponse;
import com.intellifinance.authservice.entity.User;
import com.intellifinance.authservice.enums.UserStatus;
import com.intellifinance.authservice.exception.EmailAlreadyExistsException;
import com.intellifinance.authservice.exception.PhoneAlreadyExistsException;
import com.intellifinance.authservice.repository.UserRepository;
import com.intellifinance.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        log.info("Registration request received for email: {}", request.email());

        if (userRepository.existsByEmail(request.email())) {
            log.warn("Registration failed. Email already exists: {}", request.email());
            throw new EmailAlreadyExistsException("Email already exists");
        }
        if (userRepository.existsByPhone(request.phone())) {
            log.warn("Registration failed. Phone number already exists: {}", request.phone());
            throw new PhoneAlreadyExistsException("Phone number already exists");
        }
        User user = User.builder()
                .fullName(request.fullName())
                .email(request.email())
                .phone(request.phone())
                .password(passwordEncoder.encode(request.password()))
                .status(UserStatus.ACTIVE)
                .build();
        User savedUser = userRepository.save(user);
        log.info("User registered successfully. UserId: {}", savedUser.getId());
        return new RegisterResponse(
                savedUser.getId(),
                "User Register Succesfully"
        );
    }
}
