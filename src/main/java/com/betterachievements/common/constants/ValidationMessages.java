package com.betterachievements.common.constants;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationMessages {

    public static final String LENGTH = "must be between {min} and {max} characters long";
    public static final String EMAIL = "must be a well-formed email address";
}
