package com.example.clinic_management.service.auth;

import com.example.clinic_management.dto.auth.LoginDto;
import com.example.clinic_management.dto.auth.RegisterDto;
import com.example.clinic_management.dto.auth.LoginResponseDto;

public interface AuthService {
    public String register(RegisterDto registerDto);
    public LoginResponseDto login(LoginDto loginDto);
}
