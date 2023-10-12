package com.sourcery.oirs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String fullName;
    private String department;
    private String role;
    private String address;
    private String city;
    private String state;
    private String postcode;
    private String country;
}
