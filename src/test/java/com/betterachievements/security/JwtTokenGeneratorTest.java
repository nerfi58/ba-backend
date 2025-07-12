package com.betterachievements.security;

import com.betterachievements.common.testutil.JwtTestUtils;
import jakarta.json.JsonObject;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtTokenGeneratorTest {

    private static final String JWT_ISSUER = "testIssuer";
    private static final Clock FIXED_CLOCK = Clock.fixed(Instant.parse("2025-07-10T12:00:00Z"), ZoneOffset.UTC);

    private final JwtTokenGenerator jwtTokenGenerator = new JwtTokenGenerator(JWT_ISSUER, FIXED_CLOCK);

    @Test
    void shouldGenerateValidJwtToken() {
        String userId = "user123";
        Duration lifetime = Duration.ofHours(2);

        String token = jwtTokenGenerator.generateJwt(userId, lifetime);

        assertThat(token).isNotBlank();

        JsonObject payload = JwtTestUtils.decodePayload(token);

        assertThat(payload.getString("iss")).isEqualTo(JWT_ISSUER);
        assertThat(payload.getString("sub")).isEqualTo(userId);
        assertThat(payload.getJsonNumber("iat").longValue()).isEqualTo(Instant.now(FIXED_CLOCK).getEpochSecond());
        assertThat(payload.getJsonNumber("exp").longValue()).isEqualTo(Instant.now(FIXED_CLOCK).plus(lifetime).getEpochSecond());
    }
}
