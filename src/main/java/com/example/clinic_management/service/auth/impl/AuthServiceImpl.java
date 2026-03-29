package com.example.clinic_management.service.auth.impl;

import com.example.clinic_management.dto.auth.LoginDto;
import com.example.clinic_management.dto.auth.RegisterDto;
import com.example.clinic_management.dto.auth.LoginResponseDto;
import com.example.clinic_management.entity.RefreshToken;
import com.example.clinic_management.entity.Role;
import com.example.clinic_management.entity.User;
import com.example.clinic_management.exception.InvalidCredentialException;
import com.example.clinic_management.exception.ResourceAlreadyExistException;
import com.example.clinic_management.repository.UserRepository;
import com.example.clinic_management.service.auth.AuthService;
import com.example.clinic_management.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper,
            JwtUtil jwtUtil,
            RefreshTokenService refreshTokenService
    )
    {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.modelMapper=modelMapper;
        this.jwtUtil=jwtUtil;
        this.refreshTokenService=refreshTokenService;
    }



    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail()))
        {
            throw new ResourceAlreadyExistException("Email already exist");
        }
        if(userRepository.existsByMobile(registerDto.getMobile()))
        {
            throw new ResourceAlreadyExistException("Mobile already exist");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setMobile(registerDto.getMobile());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(Role.PATIENT);
        User savedUser = userRepository.save(user);
        return "User registered successfully";
    }



    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        User logedinUser =
               userRepository.findByEmail(loginDto.getEmail());
        if(!passwordEncoder.matches(loginDto.getPassword(),logedinUser.getPassword()))
        {
            throw  new InvalidCredentialException("Invalid Credentials");
        }

        String accessToken = jwtUtil.generateToken(loginDto.getEmail(),logedinUser.getRole());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(logedinUser);
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setAccessToken(accessToken);
        responseDto.setRefreshToken(refreshToken.getRefreshToken());

        return responseDto;

    }


}
