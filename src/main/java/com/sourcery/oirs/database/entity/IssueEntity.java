package com.sourcery.oirs.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class IssueEntity {

    private UUID id;
    private String name;
    private String status;
    private String description;
    private Double commentCount;
    private Timestamp startTime;
    private Timestamp finishTime;
    private UUID employeeId;
    private UUID officeId;
    private int rating;

    }
