package com.sourcery.oirs.service;

import com.sourcery.oirs.exceptions.UserNotAuthenticatedException;
import com.sourcery.oirs.model.LoginResponse;
import com.sourcery.oirs.model.RefreshResponse;
import com.sourcery.oirs.model.User;
import com.sourcery.oirs.security.CustomUserDetails;
import com.sourcery.oirs.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public LoginResponse login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = customUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        String token = jwtIssuer.issue(customUserDetails.getId(),
                customUserDetails.getEmail(),
                customUserDetails.getName(),
                customUserDetails.getPosition(),
                roles);
        User user = userService.getUserById(customUserDetails.getId());
        return LoginResponse.builder()
                .jwt(token)
                .user(user)
                .build();
    }

    public RefreshResponse refreshSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthenticatedException("User not authenticated");
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = customUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        String token = jwtIssuer.issue(customUserDetails.getId(),
                customUserDetails.getEmail(),
                customUserDetails.getName(),
                customUserDetails.getPosition(),
                roles);
        return RefreshResponse.builder().jwt(token).build();
    }
}

