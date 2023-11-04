package com.sourcery.oirs.model;

import com.sourcery.oirs.database.entity.CountryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Country {
    String name;

    public static Country convert(CountryEntity entity) {
        return Country.builder()
                .name(entity.getName())
                .build();
    }
}
