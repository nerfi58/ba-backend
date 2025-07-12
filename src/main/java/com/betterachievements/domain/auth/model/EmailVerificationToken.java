package com.betterachievements.domain.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "email_verification_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class EmailVerificationToken {

    public EmailVerificationToken(String token, UserAuth userAuth, Instant expiresAt) {
        this.token = token;
        this.userAuth = userAuth;
        this.expiresAt = expiresAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "bigint", unique = true, nullable = false, updatable = false)
    private long id;

    @Column(name = "token", columnDefinition = "varchar(32)", unique = true, nullable = false, updatable = false)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "uuid", nullable = false, updatable = false)
    private UserAuth userAuth;

    @Column(name = "expires_at", columnDefinition = "timestamp", nullable = false, updatable = false)
    private Instant expiresAt;
}
