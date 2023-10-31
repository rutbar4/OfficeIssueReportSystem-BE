package com.sourcery.oirs.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtDecoder {
    private final RsaKeyProperties keys;

    public DecodedJWT decode(String token) {
    Algorithm algorithm = Algorithm.RSA256( keys.getPublicKey());
    return JWT.require(algorithm)
            .build()
            .verify(token);
    }
}
