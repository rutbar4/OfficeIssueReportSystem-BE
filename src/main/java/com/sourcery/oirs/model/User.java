package com.sourcery.oirs.model;

import com.sourcery.oirs.database.entity.AddressEntity;
import com.sourcery.oirs.database.entity.CountryEntity;
import com.sourcery.oirs.database.entity.OfficeEntity;
import com.sourcery.oirs.database.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private UUID id;
    private String fullName;
    private String email;
    private String position;
    private List<Role> roles;
    private String avatar;
    private Address address;
    private Country country;
    private Office office;

    public static User convert(UserEntity entity, CountryEntity countryEntity, AddressEntity addressEntity, OfficeEntity officeEntity) {
        return User.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .position(entity.getPosition())
                .roles(entity.getRoles())
                .avatar(entity.getAvatar())
                .address(Address.convert(addressEntity))
                .country(Country.convert(countryEntity))
                .office(Office.convert(officeEntity))
                .build();
    }
}

