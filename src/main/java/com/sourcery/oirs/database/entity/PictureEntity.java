package com.sourcery.oirs.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PictureEntity {
    private UUID id;
    private String url;
    private UUID issueId;
    private UUID userId;
}
