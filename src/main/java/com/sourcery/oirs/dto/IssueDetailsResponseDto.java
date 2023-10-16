package com.sourcery.oirs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class IssueDetailsResponseDto{
    private UUID id;
    private String name;
    private String description;
    private String status;
    private Long rating;
    private LocalDate dateCreated;
    private String employeeName;
    private String officeName;
}
