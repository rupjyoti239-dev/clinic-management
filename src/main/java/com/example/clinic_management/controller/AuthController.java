package com.example.clinic_management.controller;

import com.example.clinic_management.dto.auth.LoginDto;
import com.example.clinic_management.dto.auth.RegisterDto;
import com.example.clinic_management.dto.auth.LoginResponseDto;
import com.example.clinic_management.dto.doctor.DoctorRequest;
import com.example.clinic_management.dto.doctor.DoctorResponse;
import com.example.clinic_management.service.auth.AuthService;
import com.example.clinic_management.service.auth.impl.RefreshTokenService;
import com.example.clinic_management.service.doctor.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController  {

    private final AuthService authService;
    private final DoctorService doctorService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(
            AuthService authService,
            DoctorService doctorService,
            RefreshTokenService refreshTokenService
    )
    {
        this.authService=authService;
        this.doctorService=doctorService;
        this.refreshTokenService=refreshTokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto)
    {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto)
    {
        LoginResponseDto response = authService.login(loginDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(@RequestParam String token)
    {
        LoginResponseDto responseDto = refreshTokenService.refreshToken(token);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/doctor")
    public ResponseEntity<DoctorResponse> createDoctor(@Valid @RequestBody DoctorRequest doctorRequest)
    {
        DoctorResponse response = doctorService.createDoctor(doctorRequest);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
