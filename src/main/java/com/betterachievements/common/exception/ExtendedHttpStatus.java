package com.betterachievements.common.exception;

import jakarta.ws.rs.core.Response;

public enum ExtendedHttpStatus implements Response.StatusType {

    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity");

    private final int statusCode;
    private final String reasonPhrase;

    ExtendedHttpStatus(int statusCode, String reasonPhrase) {
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public Response.Status.Family getFamily() {
        return Response.Status.Family.familyOf(statusCode);
    }

    @Override
    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
