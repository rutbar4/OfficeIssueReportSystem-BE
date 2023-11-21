package com.sourcery.oirs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vote {
    private UUID id;
    private UUID issueId;
    private UUID employeeId;
}
