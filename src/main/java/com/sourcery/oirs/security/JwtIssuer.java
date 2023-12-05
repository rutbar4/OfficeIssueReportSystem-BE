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
    private final RsaKeyProperties keys;

    public String issue(UUID id, String email, String name, String position, List<String> roles) {
        Algorithm algorithm = Algorithm.RSA256(keys.getPrivateKey());
        return JWT.create()
                .withSubject(String.valueOf(id))
                .withExpiresAt(Instant.now().plus(Duration.of(3, ChronoUnit.MINUTES)))
                .withClaim("email", email)
                .withClaim("fullName", name)
                .withClaim("position", position)
                .withClaim("roles", roles)
                .sign(algorithm);
    }
}
