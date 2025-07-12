package com.betterachievements.domain.auth.dto.response;

public record AccessTokenResponse(
        String accessToken,
        String tokenType,
        int expiresIn
) {}
