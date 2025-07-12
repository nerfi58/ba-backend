package com.betterachievements.domain.auth.service;

import com.betterachievements.domain.auth.dto.request.RegisterRequest;
import com.betterachievements.domain.auth.model.UserAuth;
import com.betterachievements.security.PasswordEncoder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserAuthService userAuthService;
    private final EmailVerificationService emailVerificationService;

    @Transactional
    public String register(RegisterRequest registerRequest) {
        String passwordHash = passwordEncoder.hashPassword(registerRequest.password());
        UserAuth userAuth = new UserAuth(registerRequest.email(), passwordHash);
        UserAuth savedUser = userAuthService.save(userAuth);

        emailVerificationService.createAndSendVerificationEmail(savedUser);
        return savedUser.getEmail();
    }

    @Transactional
    public void verifyEmail(String token) {
        emailVerificationService.verifyEmail(token);
    }
}
