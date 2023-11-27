package com.sourcery.oirs.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class UserMainInfoResponseDTO {
    private UUID id;
    private String fullName;
}
