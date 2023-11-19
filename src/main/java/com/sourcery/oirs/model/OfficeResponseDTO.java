package com.sourcery.oirs.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
@SuperBuilder(toBuilder = true)
public class OfficeResponseDTO {
    private UUID id;
    private String name;
    private String country;
}
