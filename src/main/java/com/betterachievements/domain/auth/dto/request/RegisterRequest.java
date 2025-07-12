package com.betterachievements.domain.auth.dto.request;

import com.betterachievements.common.constants.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegisterRequest(
        @NotBlank
        @Email(message = ValidationMessages.EMAIL)
        String email,

        @NotBlank
        @Length(min = 8, max = 32, message = ValidationMessages.LENGTH)
        String password
) {}
