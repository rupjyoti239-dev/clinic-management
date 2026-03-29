package com.example.clinic_management.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotBlank(message = "name cannot be empty")
    private String name;
    @Email(message = "email must be a valid email")
    private String email;
    @NotBlank(message = "password cannot be empty")
    @Size(min = 6,max = 16)
    private String password;
    @NotBlank(message = "mobile cannot be empty")
    private String mobile;
}
