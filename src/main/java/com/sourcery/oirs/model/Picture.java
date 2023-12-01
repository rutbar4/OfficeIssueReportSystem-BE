package com.sourcery.oirs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Picture {
    private UUID id;
    private String url;
    private UUID issueId;
    private UUID employeeId;
}
