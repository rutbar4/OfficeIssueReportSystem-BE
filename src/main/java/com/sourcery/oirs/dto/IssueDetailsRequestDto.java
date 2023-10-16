package com.sourcery.oirs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class IssueDetailsRequestDto {
    @NotBlank
    @Size(max = 150)
    private String name;
    @NotBlank
    @Size(max = 15)
    private String status;
    private LocalDate dateCreated;
    private Long rating;
    @NotBlank
    @Size(max = 254)
    private String description;
}
