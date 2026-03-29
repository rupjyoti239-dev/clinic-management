package com.example.clinic_management.service.auth.impl;

import com.example.clinic_management.dto.auth.LoginResponseDto;
import com.example.clinic_management.entity.RefreshToken;
import com.example.clinic_management.entity.User;
import com.example.clinic_management.exception.ResourceNotFoundException;
import com.example.clinic_management.repository.RefreshTokenRepository;
import com.example.clinic_management.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,JwtUtil jwtUtil)
    {
        this.refreshTokenRepository=refreshTokenRepository;
        this.jwtUtil=jwtUtil;
    }


    @Transactional
    public RefreshToken createRefreshToken(User user)
    {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(7));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);

    }


    @Transactional
    public  RefreshToken verifyRefreshToken(String refreshToken)
    {
        RefreshToken refreshToken1 =
                refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()-> new ResourceNotFoundException("Invalid Token"));
        if(refreshToken1.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Invalid Token");
        }
        return refreshToken1;
    }

    @Transactional
    public LoginResponseDto refreshToken(String token)
    {
        RefreshToken oldToken = verifyRefreshToken(token);
        User user = oldToken.getUser();
        refreshTokenRepository.delete(oldToken);
        RefreshToken newRefreshToken = createRefreshToken(user);
        String accessToken = jwtUtil.generateToken(user.getEmail(),user.getRole());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setAccessToken(accessToken);
        loginResponseDto.setRefreshToken(newRefreshToken.getRefreshToken());
        return loginResponseDto;
    }

    @Transactional
    public void deleteExpiredTokens() {
        refreshTokenRepository.deleteByExpiryDateBefore(LocalDateTime.now());
    }



}
