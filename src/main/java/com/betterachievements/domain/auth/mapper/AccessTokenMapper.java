package com.betterachievements.domain.auth.mapper;

import com.betterachievements.domain.auth.dto.internal.AccessTokenDto;
import com.betterachievements.domain.auth.dto.response.AccessTokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface AccessTokenMapper {

    @Mapping(target = "accessToken", source = "token")
    @Mapping(target = "tokenType", constant = "Bearer")
    AccessTokenResponse toAccessTokenResponse(AccessTokenDto tokenDto);
}
