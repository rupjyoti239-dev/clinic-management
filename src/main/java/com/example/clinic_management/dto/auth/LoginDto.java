package com.example.clinic_management.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LoginDto {
    @Email(message = "email must be a valid email")
    private String email;
    @NotBlank(message = "password cannot be empty")
    private String password;
}
