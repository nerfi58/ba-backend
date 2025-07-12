package com.betterachievements.domain.auth.mapper;

import com.betterachievements.domain.auth.dto.internal.AccessTokenDto;
import com.betterachievements.domain.auth.dto.response.AccessTokenResponse;
import jakarta.enterprise.context.ApplicationScoped;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-12T03:48:37+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.jar, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@ApplicationScoped
public class AccessTokenMapperImpl implements AccessTokenMapper {

    @Override
    public AccessTokenResponse toAccessTokenResponse(AccessTokenDto tokenDto) {
        if ( tokenDto == null ) {
            return null;
        }

        String accessToken = null;
        int expiresIn = 0;

        accessToken = tokenDto.token();
        expiresIn = (int) tokenDto.expiresIn();

        String tokenType = "Bearer";

        AccessTokenResponse accessTokenResponse = new AccessTokenResponse( accessToken, tokenType, expiresIn );

        return accessTokenResponse;
    }
}
