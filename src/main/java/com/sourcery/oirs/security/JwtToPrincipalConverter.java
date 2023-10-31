package com.sourcery.oirs.security;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sourcery.oirs.model.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class JwtToPrincipalConverter {
    public CustomUserDetails convert(DecodedJWT jwt) {
        return CustomUserDetails.builder()
                .id(UUID.fromString(jwt.getSubject()))
                .email(jwt.getClaim("email").asString())
                .name(jwt.getClaim("fullName").asString())
                .position(jwt.getClaim("position").asString())
                .roles(extractAuthFromClaim(jwt).stream()
                        .map(SimpleGrantedAuthority::getAuthority)
                        .map(Role::valueOf).toList())
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthFromClaim(DecodedJWT jwt) {
        Claim claim = jwt.getClaim("roles");
        if (claim.isNull() || claim.isMissing()) {
            return List.of();
        }
        return claim.asList(SimpleGrantedAuthority.class);
    }
}

