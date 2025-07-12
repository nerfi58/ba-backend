package com.betterachievements.security;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomTokenGeneratorTest {

    private final RandomTokenGenerator randomTokenGenerator = new RandomTokenGenerator();

    @Test
    void shouldGenerateNotNullToken() {
        String token = randomTokenGenerator.generateToken(32);

        assertThat(token).isNotBlank();
    }

    @Test
    void shouldGenerateTokenWithCorrectLength() {
        int[] lengths = {8, 16, 24, 28, 32, 48, 64};

        for (int length : lengths) {
            String token = randomTokenGenerator.generateToken(length);

            int expectedLength = (int) Math.ceil(length * 8.0 / 6.0);
            assertThat(token).hasSize(expectedLength);
        }
    }

    @Test
    void shouldGenerateUniqueTokens() {
        int iterations = 100000;
        Set<String> tokens = new HashSet<>();

        for (int i = 0; i < iterations; i++) {
            tokens.add(randomTokenGenerator.generateToken(32));
        }

        assertThat(tokens).hasSize(iterations);
    }
}
