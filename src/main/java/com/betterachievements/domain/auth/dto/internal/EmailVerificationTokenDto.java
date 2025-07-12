package com.betterachievements.domain.auth.dto.internal;

public record EmailVerificationTokenDto(
        String token,
        long expiresIn
) {}
