package com.sourcery.oirs.model;

import com.sourcery.oirs.database.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String state;
    private String postcode;
    private UUID countryId;

    public static Address convert(AddressEntity entity) {
        return Address.builder()
                .street(entity.getStreet())
                .city(entity.getCity())
                .state(entity.getState())
                .postcode(entity.getPostcode())
                .build();
    }


}