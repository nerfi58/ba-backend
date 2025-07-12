package com.betterachievements.domain.auth.service;

import com.betterachievements.domain.auth.exception.EmailAlreadyVerifiedException;
import com.betterachievements.domain.auth.exception.EmailVerificationTokenExpired;
import com.betterachievements.domain.auth.model.EmailVerificationToken;
import com.betterachievements.domain.auth.model.UserAuth;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmailVerificationServiceTest {

    @Mock
    EmailVerificationTokenService emailVerificationTokenService;

    @Mock
    UserAuthService userAuthService;

    @Mock
    VerificationEmailSender verificationEmailSender;

    @Mock
    Clock clock;

    @InjectMocks
    EmailVerificationService emailVerificationService;

    @Test
    void shouldCreateAndSendVerificationEmailWhenTokenIsCorrect() {
        when(clock.instant()).thenReturn(Instant.parse("2025-07-10T12:00:00Z"));
        UserAuth userAuth = new UserAuth("test@example.com", "hashedPassword");
        userAuth.setEmailVerified(false);
        EmailVerificationToken token = new EmailVerificationToken("token", userAuth, Instant.now(clock).plus(2, ChronoUnit.HOURS));
        when(emailVerificationTokenService.generateAndSaveToken(any(UserAuth.class))).thenReturn(token);

        emailVerificationService.createAndSendVerificationEmail(userAuth);

        verify(verificationEmailSender).sendVerificationEmail(userAuth.getEmail(), token.getToken());
    }

    @Test
    void shouldThrowExceptionDuringTokenCreationWhenEmailAlreadyVerified() {
        UserAuth userAuth = new UserAuth("test@example.com", "hashedPassword");
        userAuth.setEmailVerified(true);

        Throwable thrown = catchThrowable(() -> emailVerificationService.createAndSendVerificationEmail(userAuth));

        assertThat(thrown).isInstanceOf(EmailAlreadyVerifiedException.class);
        assertThat(((EmailAlreadyVerifiedException) thrown).getParameters()).containsExactly("test@example.com");
    }

    @Test
    void shouldCallVerifyUserEmailIfTokenIsCorrectAndDeleteToken() {
        when(clock.instant()).thenReturn(Instant.parse("2025-07-10T12:00:00Z"));
        String token = "verificationToken";
        UserAuth userAuth = new UserAuth("text@example.com", "password");
        EmailVerificationToken evToken = new EmailVerificationToken(token, userAuth, Instant.now(clock).plus(2, ChronoUnit.HOURS));
        when(emailVerificationTokenService.findByTokenWithUserAuth(token)).thenReturn(evToken);

        emailVerificationService.verifyEmail(token);

        verify(userAuthService).verifyUserEmail(userAuth);
        verify(emailVerificationTokenService).deleteByToken(token);
    }

    @Test
    void shouldThrowExceptionDuringVerificationWhenTokenIsExpired() {
        when(clock.instant()).thenReturn(Instant.parse("2025-07-10T12:00:00Z"));
        String token = "verificationToken";
        UserAuth userAuth = new UserAuth("text@example.com", "password");
        EmailVerificationToken evToken = new EmailVerificationToken(token, userAuth, Instant.now(clock).minus(2, ChronoUnit.HOURS));
        when(emailVerificationTokenService.findByTokenWithUserAuth(token)).thenReturn(evToken);

        Throwable thrown = catchThrowable(() -> emailVerificationService.verifyEmail(token));

        assertThat(thrown).isInstanceOf(EmailVerificationTokenExpired.class);
        assertThat(((EmailVerificationTokenExpired) thrown).getParameters()).containsExactly(token);
    }

    @Test
    void shouldThrowExceptionDuringVerificationWhenEmailIsAlreadyVerified() {
        when(clock.instant()).thenReturn(Instant.parse("2025-07-10T12:00:00Z"));
        String token = "verificationToken";
        UserAuth userAuth = new UserAuth("text@example.com", "password");
        userAuth.setEmailVerified(true);
        EmailVerificationToken evToken = new EmailVerificationToken(token, userAuth, Instant.now(clock).plus(2, ChronoUnit.HOURS));
        when(emailVerificationTokenService.findByTokenWithUserAuth(token)).thenReturn(evToken);

        Throwable thrown = catchThrowable(() -> emailVerificationService.verifyEmail(token));

        assertThat(thrown).isInstanceOf(EmailAlreadyVerifiedException.class);
        assertThat(((EmailAlreadyVerifiedException) thrown).getParameters()).containsExactly(userAuth.getEmail());
    }
}
