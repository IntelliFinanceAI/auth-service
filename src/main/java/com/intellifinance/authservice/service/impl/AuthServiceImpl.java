package com.intellifinance.authservice.service.impl;

import com.intellifinance.authservice.dto.request.LoginRequest;
import com.intellifinance.authservice.dto.request.RegisterRequest;
import com.intellifinance.authservice.dto.response.LoginResponse;
import com.intellifinance.authservice.dto.response.RegisterResponse;
import com.intellifinance.authservice.entity.User;
import com.intellifinance.authservice.enums.UserStatus;
import com.intellifinance.authservice.exception.EmailAlreadyExistsException;
import com.intellifinance.authservice.exception.InvalidCredentialsException;
import com.intellifinance.authservice.exception.PhoneAlreadyExistsException;
import com.intellifinance.authservice.mapper.UserMapper;
import com.intellifinance.authservice.repository.UserRepository;
import com.intellifinance.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.intellifinance.authservice.constants.ErrorMessages.EMAIL_ALREADY_EXISTS;
import static com.intellifinance.authservice.constants.ErrorMessages.PHONE_NUMBER_ALREADY_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;


    /**
     * register a user.....
     * @param request
     * @return
     */
    @Override
    public RegisterResponse register(RegisterRequest request) {

        log.info("Registration request received for email: {}", request.email());

        if (userRepository.existsByEmail(request.email())) {
            log.warn("Registration failed. Email already exists: {}", request.email());
            throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXISTS);
        }
        if (userRepository.existsByPhone(request.phone())) {
            log.warn("Registration failed. Phone number already exists: {}", request.phone());
            throw new PhoneAlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS);
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setStatus(UserStatus.ACTIVE);

        User savedUser = userRepository.save(user);
        log.info(
                "User registration completed successfully. UserId={}, Email={}",
                savedUser.getId(),
                savedUser.getEmail()
        );
        return userMapper.toRegisterResponse(savedUser);

    }

    // login service.....
    public LoginResponse loginUser(LoginRequest request){
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if(!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String accessToken = jwtService.generateJwtToken(user);

        return userMapper.toLoginResponse(
                user,
                accessToken,
                "dummyRefreshToken"  // Replace with actual token generation logic)
        );
    }
}
