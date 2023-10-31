package com.sourcery.oirs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginRequest {
    String email;
    String password;
}

