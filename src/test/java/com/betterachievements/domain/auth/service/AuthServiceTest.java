package com.betterachievements.domain.auth.service;

import com.betterachievements.domain.auth.dto.request.RegisterRequest;
import com.betterachievements.domain.auth.model.UserAuth;
import com.betterachievements.security.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserAuthService userAuthService;

    @Mock
    private EmailVerificationService emailVerificationService;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldRegisterUser() {
        RegisterRequest registerRequest = new RegisterRequest("test@example.com", "superpassword123");
        when(passwordEncoder.hashPassword("superpassword123")).thenReturn("hashed");

        UserAuth saved = new UserAuth("test@example.com", "hashed");
        when(userAuthService.save(any(UserAuth.class))).thenReturn(saved);

        String result = authService.register(registerRequest);

        verify(passwordEncoder).hashPassword("superpassword123");
        ArgumentCaptor<UserAuth> captor = ArgumentCaptor.forClass(UserAuth.class);
        verify(userAuthService).save(captor.capture());
        UserAuth user = captor.getValue();

        assertThat(user.getEmail()).isEqualTo("test@example.com");
        assertThat(user.getPasswordHash()).isEqualTo("hashed");

        verify(emailVerificationService).createAndSendVerificationEmail(saved);
        assertThat(result).isEqualTo("test@example.com");
    }
}
