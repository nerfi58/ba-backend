package com.betterachievements.domain.auth.exception;

import com.betterachievements.common.exception.BetterAchievementsException;
import com.betterachievements.common.exception.ErrorCode;

public class EmailVerificationTokenExpired extends BetterAchievementsException {
    private static final ErrorCode ERROR_CODE = ErrorCode.EMAIL_VERIFICATION_TOKEN_EXPIRED;
    public EmailVerificationTokenExpired(String token) {
        super(ERROR_CODE, token);
    }
}
