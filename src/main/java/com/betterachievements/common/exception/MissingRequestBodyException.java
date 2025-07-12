package com.betterachievements.common.exception;

public class MissingRequestBodyException extends BetterAchievementsException {
    private static final ErrorCode ERROR_CODE = ErrorCode.MISSING_REQUEST_BODY;
    public MissingRequestBodyException() {
        super(ERROR_CODE);
    }
}
