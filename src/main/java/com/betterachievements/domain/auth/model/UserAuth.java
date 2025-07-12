package com.betterachievements.domain.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user_auth")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuth {

    public UserAuth(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.emailVerified = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(name = "email", columnDefinition = "varchar(256)", unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", columnDefinition = "varchar(256)", nullable = false)
    private String passwordHash;

    @Column(name = "email_verified", columnDefinition = "boolean", nullable = false)
    @Setter
    private boolean emailVerified;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "timestamp", nullable = false)
    private Instant updatedAt;
}
