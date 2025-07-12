package com.betterachievements.infrastructure.email;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class EmailService {

    private final Mailer mailer;

    public void sendHtmlEmail(String to, String subject, String html) {
        mailer.send(Mail.withHtml(to, subject, html));
    }
}
