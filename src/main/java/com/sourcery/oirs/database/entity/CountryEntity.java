package com.sourcery.oirs.database.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class CountryEntity {
    private UUID id;
    String name;
}
