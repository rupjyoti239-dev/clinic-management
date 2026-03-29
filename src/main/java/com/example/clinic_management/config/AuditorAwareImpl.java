package com.example.clinic_management.config;

import com.example.clinic_management.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        // 🔥 for register (no login)
        if (auth == null || !auth.isAuthenticated()) {
            return Optional.of("SYSTEM");
        }

        Object principal = auth.getPrincipal();

        if (principal instanceof User user) {
            return Optional.of(user.getEmail());
        }

        return Optional.of("SYSTEM"); // 🔥 NEVER NULL
    }
}
