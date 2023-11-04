package com.sourcery.oirs.database.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressEntity {
    private UUID id;
    private String street;
    private String city;
    private String state;
    private String postcode;
    private UUID countryId;
}
