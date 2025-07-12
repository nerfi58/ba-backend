package com.betterachievements.security;

import com.password4j.Argon2Function;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Argon2;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class PasswordEncoder {

    private final String pepper;

    @Inject
    public PasswordEncoder(@ConfigProperty(name = "security.password.pepper") String pepper) {
        this.pepper = pepper;
    }

    public String hashPassword(String password) {
        Hash hash = Password.hash(password)
                .addRandomSalt(32)
                .addPepper(pepper)
                .with(Argon2Function.getInstance(65536, 3, 1, 32, Argon2.ID));

        return hash.getResult();
    }
}
