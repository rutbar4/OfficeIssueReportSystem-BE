package com.sourcery.oirs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sourcery.oirs.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity {
    private UUID id;
    private String fullName;
    private String email;
    @JsonIgnore
    private String password;
    private List<Role> roles;
    private String phoneNumber;
    private String avatar;
}

