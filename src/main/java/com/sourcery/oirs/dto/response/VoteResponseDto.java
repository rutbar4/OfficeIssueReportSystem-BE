package com.sourcery.oirs.dto.response;

import com.sourcery.oirs.dto.VoteRequestDto;
import com.sourcery.oirs.model.Vote;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class VoteResponseDto extends VoteRequestDto {
    private UUID id;

    public static VoteResponseDto of(Vote vote) {
        return VoteResponseDto.builder()
                .id(vote.getId())
                .issueId(vote.getIssueId())
                .employeeId(vote.getEmployeeId())
                .build();
    }
}
