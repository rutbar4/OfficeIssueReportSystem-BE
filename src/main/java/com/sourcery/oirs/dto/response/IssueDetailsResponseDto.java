package com.sourcery.oirs.dto.response;

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
    private int rating;
    private Long commentCount;
    private LocalDate dateCreated;
    private String employeeName;
    private String officeName;
    private String officeId;
    private String employeeId;
    public void setVoteCount(int count){
        rating = count;
    }
}
