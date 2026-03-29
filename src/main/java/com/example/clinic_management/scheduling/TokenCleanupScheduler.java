package com.example.clinic_management.scheduling;

import com.example.clinic_management.repository.RefreshTokenRepository;
import com.example.clinic_management.service.auth.impl.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TokenCleanupScheduler {
    private final RefreshTokenService refreshTokenService;


    @Scheduled(fixedRate = 3600000) //1hr
    public void deleteExpiredToken(){
        refreshTokenService.deleteExpiredTokens();
        System.out.println("Expired Token deleted");
    }
}
