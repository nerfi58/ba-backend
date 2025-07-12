package com.betterachievements.common.config;

import com.betterachievements.common.exception.MissingRequestBodyException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Provider
public class RequiredRequestBodyFilter implements ContainerRequestFilter {

    private final ObjectMapper objectMapper;
    private final ResourceInfo resourceInfo;

    public RequiredRequestBodyFilter(@Context ResourceInfo resourceInfo) {
        this.resourceInfo = resourceInfo;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println();
        Method method = resourceInfo.getResourceMethod();
        if (method == null || !hasBodyParameter(method)) {
            return;
        }

        byte[] bodyBytes = requestContext.getEntityStream().readAllBytes();
        if (isBodyEmpty(bodyBytes)) {
            throw new MissingRequestBodyException();
        }

        requestContext.setEntityStream(new ByteArrayInputStream(bodyBytes));
    }

    private boolean hasBodyParameter(Method method) {
        return Arrays.stream(method.getParameters())
                .anyMatch(parameter -> !parameter.isAnnotationPresent(QueryParam.class)
                                       && !parameter.isAnnotationPresent(PathParam.class)
                                       && !parameter.isAnnotationPresent(HeaderParam.class)
                                       && !parameter.isAnnotationPresent(CookieParam.class));
    }

    private boolean isBodyEmpty(byte[] bodyBytes) throws IOException {
        if (bodyBytes.length == 0) {
            return true;
        }

        String bodyString = new String(bodyBytes, StandardCharsets.UTF_8);
        if (bodyString.trim().isEmpty()) {
            return true;
        }

        JsonNode jsonNode = objectMapper.readTree(bodyBytes);
        return jsonNode == null || jsonNode.isNull() || jsonNode.isEmpty();
    }
}
