package com.betterachievements.domain.auth.exception;

import com.betterachievements.common.exception.BetterAchievementsException;
import com.betterachievements.common.exception.ErrorCode;

public class EmailAlreadyInUseException extends BetterAchievementsException {
    private static final ErrorCode ERROR_CODE = ErrorCode.EMAIL_ALREADY_IN_USE;
    public EmailAlreadyInUseException(String email) {
        super(ERROR_CODE, email);
    }
}
