package com.intellifinance.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "Full name is required")
        @Size(min = 2, max = 100, message = "Full name cannot exceed 100 characters")
        String fullName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid Email")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian mobile number")
        String phone,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
        String password

) {

}