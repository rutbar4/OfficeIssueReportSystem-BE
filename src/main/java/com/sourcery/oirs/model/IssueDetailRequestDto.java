package com.sourcery.oirs.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class IssueDetailRequestDto {
    private String status;
    private String description;
    private UUID officeId;
}
