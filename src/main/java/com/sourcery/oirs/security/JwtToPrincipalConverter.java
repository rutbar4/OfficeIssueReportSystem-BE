package com.sourcery.oirs.security;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sourcery.oirs.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class JwtToPrincipalConverter {
    public CustomUserDetails convert(DecodedJWT jwt) {
        return CustomUserDetails.builder()
                .id(UUID.fromString(jwt.getSubject()))
                .email(jwt.getClaim("email").asString())
                .roles(extractAuthFromClaim(jwt))
                .build();
    }

    private List<Role> extractAuthFromClaim(DecodedJWT jwt) {
        Claim claim = jwt.getClaim("authorities");
        if (claim.isNull() || claim.isMissing()) {
            return List.of();
        }
        return claim.asList(Role.class);
    }

}

