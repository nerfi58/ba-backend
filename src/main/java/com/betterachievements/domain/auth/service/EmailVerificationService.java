package com.betterachievements.domain.auth.service;

import com.betterachievements.domain.auth.exception.EmailAlreadyVerifiedException;
import com.betterachievements.domain.auth.exception.EmailVerificationTokenExpired;
import com.betterachievements.domain.auth.model.EmailVerificationToken;
import com.betterachievements.domain.auth.model.UserAuth;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Clock;
import java.time.Instant;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class EmailVerificationService {

    private final EmailVerificationTokenService emailVerificationTokenService;
    private final UserAuthService userAuthService;
    private final VerificationEmailSender verificationEmailSender;
    private final Clock clock;

    public void createAndSendVerificationEmail(UserAuth userAuth) {
        validateEmailNotVerified(userAuth);

        EmailVerificationToken token = emailVerificationTokenService.generateAndSaveToken(userAuth);
        verificationEmailSender.sendVerificationEmail(userAuth.getEmail(), token.getToken());
    }

    public void verifyEmail(String token) {
        EmailVerificationToken evToken = emailVerificationTokenService.findByTokenWithUserAuth(token);
        validateEmailVerificationToken(evToken);

        UserAuth userAuth = evToken.getUserAuth();
        userAuthService.verifyUserEmail(userAuth);

        emailVerificationTokenService.deleteByToken(token);
    }

    private void validateEmailVerificationToken(EmailVerificationToken evToken) {
        validateEmailNotVerified(evToken.getUserAuth());

        if (evToken.getExpiresAt().isBefore(Instant.now(clock))) {
            throw new EmailVerificationTokenExpired(evToken.getToken());
        }
    }

    private void validateEmailNotVerified(UserAuth userAuth) {
        if (userAuth.isEmailVerified()) {
            throw new EmailAlreadyVerifiedException(userAuth.getEmail());
        }
    }
}
