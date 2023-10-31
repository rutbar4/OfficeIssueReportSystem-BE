package com.sourcery.oirs.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;


public class UserPrincipalAuthToken extends AbstractAuthenticationToken {
    private final CustomUserDetails customUserDetails;

    public UserPrincipalAuthToken(CustomUserDetails customUserDetails) {
        super(customUserDetails.getAuthorities());
        this.customUserDetails = customUserDetails;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public CustomUserDetails getPrincipal() {
        return customUserDetails;
    }
}

