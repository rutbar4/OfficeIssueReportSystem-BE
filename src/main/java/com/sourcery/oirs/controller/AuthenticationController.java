package com.sourcery.oirs.controller;

import com.sourcery.oirs.model.LoginRequest;
import com.sourcery.oirs.model.LoginResponse;
import com.sourcery.oirs.model.RefreshResponse;
import com.sourcery.oirs.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/refresh")
    public RefreshResponse refreshSession() {
        return authenticationService.refreshSession();
    }
}

