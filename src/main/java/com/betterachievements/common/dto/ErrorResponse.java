package com.betterachievements.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.ws.rs.core.MultivaluedMap;
import java.time.Instant;
import java.util.Set;
import lombok.AllArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
public class ErrorResponse {
    private Instant timestamp;
    private String errorCode;
    private String error;
    private int statusCode;
    private String httpStatus;
    private String method;
    private String url;
    private MultivaluedMap<String, String> queryParameters;
    private MultivaluedMap<String, String> pathParameters;
    private Set<String> fieldErrors;
}