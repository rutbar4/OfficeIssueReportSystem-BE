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
    public static IssueDetailsResponseDto of(IssueDetailsResponseDto issue){
        return IssueDetailsResponseDto.builder()
                .id(issue.getId())
                .name(issue.getName())
                .description(issue.getDescription())
                .status(issue.getStatus())
                .rating(issue.getRating())
                .dateCreated(issue.getDateCreated())
                .employeeName(issue.getEmployeeName())
                .officeName(issue.getOfficeName())
                .build();
    }
}
