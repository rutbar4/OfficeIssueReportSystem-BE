package com.sourcery.oirs.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {
    private String street;
    private String city;
    private String state;
    private String postcode;

    public static Address buildMockAdress(){
        return Address.builder()
                .street("Gatve")
                .city("Kaunas")
                .state("Kauno raj.")
                .postcode("LT-12345")
                .build();
    }
}