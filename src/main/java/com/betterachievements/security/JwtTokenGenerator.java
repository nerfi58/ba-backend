package com.betterachievements.security;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class JwtTokenGenerator {

    private final String jwtIssuer;
    private final Clock clock;

    @Inject
    public JwtTokenGenerator(@ConfigProperty(name = "mp.jwt.verify.issuer") String jwtIssuer, Clock clock) {
        this.jwtIssuer = jwtIssuer;
        this.clock = clock;
    }

    public String generateJwt(String userId, Duration lifetime) {
        Instant issuedAt = Instant.now(clock);
        Instant expiresAt = issuedAt.plus(lifetime);

        return Jwt.claims()
                .issuer(jwtIssuer)
                .subject(userId)
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .sign();
    }
}
