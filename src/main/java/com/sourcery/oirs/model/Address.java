package com.sourcery.oirs.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class Address {
    private UUID id;
    private String street;
    private String city;
    private String state;
    private String postcode;
    UUID countryId;


}