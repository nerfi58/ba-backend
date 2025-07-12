package com.betterachievements.domain.auth.exception;

import com.betterachievements.common.exception.BetterAchievementsException;
import com.betterachievements.common.exception.ErrorCode;

public class EmailAlreadyVerifiedException extends BetterAchievementsException {
    private static final ErrorCode ERROR_CODE = ErrorCode.EMAIL_ALREADY_VERIFIED;
    public EmailAlreadyVerifiedException(String email) {
        super(ERROR_CODE, email);
    }
}
