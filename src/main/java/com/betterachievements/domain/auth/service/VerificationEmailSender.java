package com.betterachievements.domain.auth.service;

import com.betterachievements.infrastructure.email.EmailService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class VerificationEmailSender {

    private final EmailService emailService;
    private final Template template;

    @ConfigProperty(name = "frontend.url")
    String frontendUrl;

    @Inject
    public VerificationEmailSender(EmailService emailService, @Location("email/verification-email") Template template) {
        this.emailService = emailService;
        this.template = template;
    }

    public void sendVerificationEmail(String email, String token) {
        String verificationLink = UriBuilder.fromUri(frontendUrl)
                .path("/verify")
                .queryParam("token", token)
                .build()
                .toString();

        String content = template.data("verificationLink", verificationLink).render();
        emailService.sendHtmlEmail(email, "Better Achievements - Verify your email", content);
    }

}
