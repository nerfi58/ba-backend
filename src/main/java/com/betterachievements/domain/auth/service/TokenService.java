package com.betterachievements.domain.auth.service;

import com.betterachievements.domain.auth.dto.internal.AccessTokenDto;
import com.betterachievements.domain.auth.dto.internal.EmailVerificationTokenDto;
import com.betterachievements.security.JwtTokenGenerator;
import com.betterachievements.security.RandomTokenGenerator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Duration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
@ApplicationScoped
public class TokenService {

    private static final Duration ACCESS_TOKEN_LIFETIME = Duration.ofMinutes(30);
    private static final Duration EMAIL_VERIFICATION_TOKEN_LIFETIME = Duration.ofHours(12);

    private final JwtTokenGenerator jwtTokenGenerator;
    private final RandomTokenGenerator randomTokenGenerator;

    public AccessTokenDto generateAccessToken(String userId) {
        String token = jwtTokenGenerator.generateJwt(userId, ACCESS_TOKEN_LIFETIME);
        return new AccessTokenDto(token, ACCESS_TOKEN_LIFETIME.toSeconds());
    }

    public EmailVerificationTokenDto generateEmailVerificationToken() {
        String token = randomTokenGenerator.generateToken(24); // length 24 is used, so the token is exactly 32 chars long
        return new EmailVerificationTokenDto(token, EMAIL_VERIFICATION_TOKEN_LIFETIME.toSeconds());
    }
}
