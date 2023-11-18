package com.sourcery.oirs.model;

import com.sourcery.oirs.database.entity.OfficeEntity;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Office {
    private UUID id;
    private String name;
    private UUID country_id;

    public static Office convert(OfficeEntity entity) {
        return Office.builder()
                .name(entity.getName())
                .id(entity.getId())
                .build();
    }

}
