package com.sourcery.oirs.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcery.oirs.model.Issue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;
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
    private Long commentCount;
    private Timestamp startTime;
    private Timestamp finishTime;
    private UUID employeeId;
    private UUID officeId;
    private Long rating;

}
