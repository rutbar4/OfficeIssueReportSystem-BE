package com.sourcery.oirs.model;

import com.sourcery.oirs.database.entity.OfficeEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Office {
    private String name;

    public static Office convert(OfficeEntity entity) {
        return Office.builder()
                .name(entity.getName())
                .build();
    }

}
