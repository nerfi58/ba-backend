package com.betterachievements.infrastructure.email;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private Mailer mailer;

    @InjectMocks
    private EmailService emailService;

    @Test
    void shouldSendEmail() {
        String to = "test@example.com";
        String subject = "Hello";
        String html = "<h1>Hi</h1>";

        emailService.sendHtmlEmail(to, subject, html);

        ArgumentCaptor<Mail> captor = ArgumentCaptor.forClass(Mail.class);
        verify(mailer).send(captor.capture());

        Mail capturedMail = captor.getValue();

        assertThat(capturedMail.getTo()).containsExactly(to);
        assertThat(capturedMail.getSubject()).isEqualTo(subject);
        assertThat(capturedMail.getHtml()).isEqualTo(html);
    }
}
