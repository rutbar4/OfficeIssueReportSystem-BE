package com.sourcery.oirs.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Builder
@Data
public class CountryEntity {
    private UUID id;
    private String name;
}
