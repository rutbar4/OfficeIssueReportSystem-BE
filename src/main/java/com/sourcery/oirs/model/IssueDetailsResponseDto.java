package com.sourcery.oirs.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
@SuperBuilder(toBuilder = true)
public class IssueDetailsResponseDto{
    private UUID id;
    private String name;
    private String description;
    private String status;
    private Long rating;
    private Long commentCount;
    private LocalDate dateCreated;
    private String employeeName;
    private String officeName;
}
