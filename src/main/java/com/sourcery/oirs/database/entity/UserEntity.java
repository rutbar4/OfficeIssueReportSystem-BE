package com.sourcery.oirs.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sourcery.oirs.model.Role;
import jakarta.validation.constraints.Email;
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
    @Email
    private String email;
    @JsonIgnore
    private String password;
    private List<Role> roles;
    private String phoneNumber;
    private String position;
    private String avatar;
}

