package com.sourcery.oirs.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OfficeEntity {
    private UUID id;
    private String name;
}

