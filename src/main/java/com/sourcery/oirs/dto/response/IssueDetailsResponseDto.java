package com.sourcery.oirs.dto.response;

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
    public UUID id;
    private String name;
    private String description;
    private String status;
    private int rating;
    private Long commentCount;
    private LocalDate dateCreated;
    private String employeeName;
    private String officeName;
    public void setVoteCount(int count){
        rating = count;
    }
}
