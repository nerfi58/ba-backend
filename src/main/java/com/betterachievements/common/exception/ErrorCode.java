package com.betterachievements.common.exception;

import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // AUTH
    EMAIL_ALREADY_IN_USE("Email {0} is already in use.", Response.Status.CONFLICT),
    EMAIL_VERIFICATION_TOKEN_NOT_FOUND("Token {0} could not be found.", Response.Status.NOT_FOUND),
    EMAIL_ALREADY_VERIFIED("Email {0} is already verified", Response.Status.CONFLICT),
    EMAIL_VERIFICATION_TOKEN_EXPIRED("Token {0} has expired.", Response.Status.GONE),

    // VALIDATION
    MISSING_REQUEST_BODY("Request body is required.", Response.Status.BAD_REQUEST),
    VALIDATION_BODY_ERROR("Request body contains invalid data.", ExtendedHttpStatus.UNPROCESSABLE_ENTITY),
    VALIDATION_PARAMETER_ERROR("Request contains one or more invalid parameters.", Response.Status.BAD_REQUEST),
    INVALID_JSON_FORMAT("Request body contains invalid JSON.", Response.Status.BAD_REQUEST),

    // OTHER
    METHOD_NOT_ALLOWED("This method is not allowed for the requested URL.", Response.Status.METHOD_NOT_ALLOWED),
    URL_NOT_FOUND("The requested URL does not exist.", Response.Status.NOT_FOUND),
    UNKNOWN("Something went wrong. Please try again later or contact us if the issue persists.", Response.Status.INTERNAL_SERVER_ERROR);

    private final String message;
    private final Response.StatusType httpStatus;

    public String getCode() {
        return this.name();
    }
}
