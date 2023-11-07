package com.sourcery.oirs.database.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class OfficeEntity {
    private UUID id;
    private String name;
}

