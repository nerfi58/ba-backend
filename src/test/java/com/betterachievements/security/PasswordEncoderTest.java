package com.betterachievements.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {

    private static final String PEPPER = "testPepper123";

    private final PasswordEncoder passwordEncoder = new PasswordEncoder(PEPPER);

    @Test
    void shouldReturnNonEmptyHash() {
        String hash = passwordEncoder.hashPassword("PASSWORD");

        assertThat(hash).isNotBlank();
        assertThat(hash).isNotEqualTo("PASSWORD");
    }

    @Test
    void shouldReturnDifferentHashForSamePassword() {
        String hash1 = passwordEncoder.hashPassword("TestPassword123");
        String hash2 = passwordEncoder.hashPassword("TestPassword123");

        assertThat(hash1).isNotEqualTo(hash2);
    }

    @Test
    void shouldReturnDifferentHashForDifferentPassword() {
        String hash1 = passwordEncoder.hashPassword("TestPassword123");
        String hash2 = passwordEncoder.hashPassword("CompletelyDifferentPassword");

        assertThat(hash1).isNotEqualTo(hash2);
    }
}
