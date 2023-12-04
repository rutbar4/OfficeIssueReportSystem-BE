package com.sourcery.oirs.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime dateCreated;
    private String employeeName;
    private String employeeAvatar;
    private String officeName;
    private String officeId;
    private String employeeId;

    public void setVoteCount(int count){
        rating = count;
    }
}
