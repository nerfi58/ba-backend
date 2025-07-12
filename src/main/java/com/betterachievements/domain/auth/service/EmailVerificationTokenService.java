package com.betterachievements.domain.auth.service;

import com.betterachievements.domain.auth.dto.internal.EmailVerificationTokenDto;
import com.betterachievements.domain.auth.exception.EmailVerificationTokenNotFound;
import com.betterachievements.domain.auth.model.EmailVerificationToken;
import com.betterachievements.domain.auth.model.UserAuth;
import com.betterachievements.domain.auth.repository.EmailVerificationTokenRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Clock;
import java.time.Instant;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class EmailVerificationTokenService {

    private final EmailVerificationTokenRepository emailVerificationTokenRepository;
    private final TokenService tokenService;
    private final Clock clock;

    public EmailVerificationToken generateAndSaveToken(UserAuth userAuth) {
        EmailVerificationTokenDto evTokenDto = tokenService.generateEmailVerificationToken();
        EmailVerificationToken evToken = new EmailVerificationToken(evTokenDto.token(), userAuth, Instant.now(clock).plusSeconds(evTokenDto.expiresIn()));

        return emailVerificationTokenRepository.insert(evToken);
    }

    public EmailVerificationToken findByTokenWithUserAuth(String token) {
        return emailVerificationTokenRepository.findByTokenWithUserAuth(token).orElseThrow(() -> new EmailVerificationTokenNotFound(token));
    }

    public void deleteByToken(String token) {
        emailVerificationTokenRepository.deleteByToken(token);
    }
}
