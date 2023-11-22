package com.sourcery.oirs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Issue {
    private UUID id;
    @NotBlank
    @Size(min=10, max = 150)
    private String name;
    @NotBlank
    @Size(min=10, max = 250)
    private String description;
    private String status;
    private int upvoteCount;
    private Double commentCount;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy")
    private LocalDateTime time;
    private UUID employeeId;
    @NotBlank
    private UUID officeId;

    public void setVoteCount(int count) {
        upvoteCount = count;
    }
}

