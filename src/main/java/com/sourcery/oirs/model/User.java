package com.sourcery.oirs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private UUID id;
    private String fullName;
    private String email;
    private List<Role> roles;
    private Address address;
    private Country country;
    private Office office;
    }