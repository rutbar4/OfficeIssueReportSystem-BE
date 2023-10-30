package com.sourcery.oirs.security;

import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Such user doesn't exist."));
        return CustomUserDetails.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getFullName())
                .password(user.getPassword())
                .position(user.getPosition())
                .roles(user.getRoles())
                .build();
    }
}

