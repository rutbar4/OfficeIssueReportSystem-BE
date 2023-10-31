package com.sourcery.oirs.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Country {
    private UUID id;
    String name;
}
