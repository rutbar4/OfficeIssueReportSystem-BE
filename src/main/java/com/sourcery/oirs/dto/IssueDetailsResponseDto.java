package com.sourcery.oirs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class IssueDetailsResponseDto extends IssueDetailsRequestDto {
    private UUID id;
    private String employeeName;
    private String officeName;

    public static IssueDetailsResponseDto of(IssueDetailsResponseDto issue,String employeeName, String officeName){


        return IssueDetailsResponseDto.builder()
                .id(issue.getId())
                .name(issue.getName())
                .description(issue.getDescription())
                .status(issue.getStatus())
                .rating(issue.getRating())
                .dateCreated(issue.getDateCreated())
                .employeeName(employeeName)
                .officeName(officeName)
                .build();
    }
}
