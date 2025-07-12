package com.betterachievements.security;

import jakarta.inject.Singleton;
import java.security.SecureRandom;
import java.util.Base64;

@Singleton
public class RandomTokenGenerator {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public String generateToken(int tokenLengthInBytes) {
        byte[] bytes = new byte[tokenLengthInBytes];
        SECURE_RANDOM.nextBytes(bytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
