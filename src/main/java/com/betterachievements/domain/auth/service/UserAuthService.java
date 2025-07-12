package com.betterachievements.domain.auth.service;

import com.betterachievements.domain.auth.exception.EmailAlreadyInUseException;
import com.betterachievements.domain.auth.model.UserAuth;
import com.betterachievements.domain.auth.repository.UserAuthRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UserAuthService {

    private final UserAuthRepository userAuthRepository;

    public UserAuth save(UserAuth userAuth) {
        validateEmailUnique(userAuth.getEmail());
        return userAuthRepository.insert(userAuth);
    }

    public void verifyUserEmail(UserAuth userAuth) {
        userAuth.setEmailVerified(true);
        userAuthRepository.update(userAuth);
    }

    private void validateEmailUnique(String email) {
        if (userAuthRepository.existsByEmail(email)) {
            throw new EmailAlreadyInUseException(email);
        }
    }
}
