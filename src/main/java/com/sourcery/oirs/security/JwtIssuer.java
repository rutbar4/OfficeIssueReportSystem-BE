package com.sourcery.oirs.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwtProperties jwtProperties;

    public String issue(UUID id, String email, List<String> roles) {
        return JWT.create()
                .withSubject(String.valueOf(id))
                .withExpiresAt(Instant.now().plus(Duration.of(600, ChronoUnit.MINUTES)))
                .withClaim("email", email)
                .withClaim("authorities", roles)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}

