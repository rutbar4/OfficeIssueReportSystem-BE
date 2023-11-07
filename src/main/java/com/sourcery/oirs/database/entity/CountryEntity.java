package com.sourcery.oirs.database.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class CountryEntity {
    private UUID id;
    private String name;
}
