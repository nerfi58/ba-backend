package com.betterachievements.domain.auth.dto.internal;

public record AccessTokenDto(
        String token,
        long expiresIn
) {}
