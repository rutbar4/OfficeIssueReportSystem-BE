package com.sourcery.oirs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private String fullName;
    private Department department;
    private Role role;
    private Address address;
    private Country country;

    public static User buildMockData(){
        return User.builder()
                .fullName("John Doe")
                .department(Department.IT)
                .role(Role.EMPLOYEE)
                .address(Address.buildMockAdress())
                .country(Country.LITHUANIA)
                .build();
    }
}