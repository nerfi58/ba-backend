package com.betterachievements.api;

import com.betterachievements.domain.auth.dto.request.RegisterRequest;
import com.betterachievements.domain.auth.dto.response.RegisterResponse;
import com.betterachievements.domain.auth.service.AuthService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/auth")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class AuthResource {

    private final AuthService authService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response register(@Valid RegisterRequest registerRequest) {
        String email = authService.register(registerRequest);
        return Response.ok().entity(new RegisterResponse(email)).build();
    }

    @POST
    @Path("/verify")
    @PermitAll
    public Response verifyEmail(@QueryParam("token") @NotBlank String token) {
        authService.verifyEmail(token);
        return Response.ok().build();
    }
}
