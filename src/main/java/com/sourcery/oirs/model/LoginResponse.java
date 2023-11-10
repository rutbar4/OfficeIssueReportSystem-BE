package com.sourcery.oirs.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginResponse {
    String jwt;
    User user;
}

