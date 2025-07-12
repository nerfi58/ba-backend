package com.betterachievements.common.exception;

import com.betterachievements.common.dto.ErrorResponse;
import com.fasterxml.jackson.core.JsonParseException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.text.MessageFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final UriInfo uriInfo;
    private final Clock clock;

    @Inject
    public GlobalExceptionHandler(@Context UriInfo uriInfo, Clock clock) {
        this.uriInfo = uriInfo;
        this.clock = clock;

    }

    @ServerExceptionMapper
    public Response handleAppException(BetterAchievementsException exception, Request request) {
        ErrorCode errorCode = exception.getErrorCode();
        String message = MessageFormat.format(exception.getMessage(), (Object[]) exception.getParameters());
        ErrorResponse errorResponse = buildErrorResponse(errorCode, request, message, Collections.emptySet());

        return Response.status(errorCode.getHttpStatus()).entity(errorResponse).build();
    }

    @ServerExceptionMapper
    public Response handleConstraintViolation(ConstraintViolationException exception, Request request) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();

        Set<ConstraintViolation<?>> parameterViolations = violations.stream()
                .filter(this::isParameterViolation)
                .collect(Collectors.toSet());

        Set<ConstraintViolation<?>> bodyViolations = violations.stream()
                .filter(v -> !isParameterViolation(v))
                .collect(Collectors.toSet());

        ErrorCode errorCode;
        Set<ConstraintViolation<?>> violationsToReport;

        if (!parameterViolations.isEmpty()) {
            errorCode = ErrorCode.VALIDATION_PARAMETER_ERROR;
            violationsToReport = parameterViolations;
        } else {
            errorCode = ErrorCode.VALIDATION_BODY_ERROR;
            violationsToReport = bodyViolations;
        }

        ErrorResponse errorResponse = buildErrorResponse(
                errorCode,
                request,
                errorCode.getMessage(),
                extractConstraintViolations(violationsToReport)
        );
        return Response.status(errorCode.getHttpStatus()).entity(errorResponse).build();
    }

    @ServerExceptionMapper
    public Response handleJsonParseException(JsonParseException exception, Request request) {
        ErrorCode errorCode = ErrorCode.INVALID_JSON_FORMAT;
        ErrorResponse errorResponse = buildErrorResponse(errorCode, request, errorCode.getMessage(), Collections.emptySet());
        return Response.status(errorCode.getHttpStatus()).entity(errorResponse).build();
    }

    @ServerExceptionMapper
    public Response handleMethodNotAllowedException(NotAllowedException exception, Request request) {
        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        ErrorResponse errorResponse = buildErrorResponse(errorCode, request, errorCode.getMessage(), Collections.emptySet());
        return Response.status(errorCode.getHttpStatus()).entity(errorResponse).build();
    }

    @ServerExceptionMapper
    public Response handleUrlNotFoundException(NotFoundException exception, Request request) {
        ErrorCode errorCode = ErrorCode.URL_NOT_FOUND;
        ErrorResponse errorResponse = buildErrorResponse(errorCode, request, errorCode.getMessage(), Collections.emptySet());
        return Response.status(errorCode.getHttpStatus()).entity(errorResponse).build();
    }

    @ServerExceptionMapper
    public Response handleOtherExceptions(Exception exception, Request request) {
        LOGGER.error("{}: {}", exception.getClass().getName(), exception.getMessage());
        ErrorCode errorCode = ErrorCode.UNKNOWN;
        ErrorResponse errorResponse = buildErrorResponse(errorCode, request, errorCode.getMessage(), Collections.emptySet());
        return Response.status(errorCode.getHttpStatus()).entity(errorResponse).build();
    }

    private Set<String> extractConstraintViolations(Set<ConstraintViolation<?>> violations) {
        return violations.stream().map(violation -> {
            String fullPath = violation.getPropertyPath().toString();
            String[] parts = fullPath.split("\\.");
            String propertyName = parts[parts.length - 1];
            return propertyName + ": " + violation.getMessage();
        }).collect(Collectors.toSet());
    }

    private ErrorResponse buildErrorResponse(ErrorCode errorCode, Request request, String message, Set<String> violations) {
        return new ErrorResponse(
                Instant.now(clock),
                errorCode.getCode(),
                message,
                errorCode.getHttpStatus().getStatusCode(),
                errorCode.getHttpStatus().getReasonPhrase(),
                request.getMethod(),
                uriInfo.getPath(),
                uriInfo.getQueryParameters(),
                uriInfo.getPathParameters(),
                violations
        );
    }

    private boolean isParameterViolation(ConstraintViolation<?> violation) {
        String propertyPath = violation.getPropertyPath().toString();

        return propertyPath.contains("arg") || propertyPath.matches("\\w+\\.\\w+");
    }
}
