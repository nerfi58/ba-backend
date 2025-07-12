package com.betterachievements.common.exception;

import lombok.Getter;

@Getter
public class BetterAchievementsException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String[] parameters;

    public BetterAchievementsException(ErrorCode errorCode, String... parameters) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.parameters = parameters;
    }
}
