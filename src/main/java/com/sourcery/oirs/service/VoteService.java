package com.sourcery.oirs.service;

import com.sourcery.oirs.database.repository.IssueRepository;
import com.sourcery.oirs.dto.response.IsVotedResponseDto;
import com.sourcery.oirs.dto.response.VoteCountResponseDto;
import com.sourcery.oirs.dto.response.VoteResponseDto;
import com.sourcery.oirs.model.Vote;
import com.sourcery.oirs.database.repository.VoteRepository;
import com.sourcery.oirs.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    @Transactional
    public VoteResponseDto createVote (UUID issueId, UUID employeeId) {
        var issue = issueRepository.findById(issueId);
        if (issue.isEmpty()) {
            return null;
        }

        var user = userRepository.findById(employeeId);
        if (user.isEmpty()) {
            return null;
        }

        Vote vote = Vote.builder()
                .id(UUID.randomUUID())
                .issueId(issueId)
                .employeeId(employeeId)
                .build();

        voteRepository.insert(vote);
        return VoteResponseDto.of(vote);
    }

    @Transactional
    public IsVotedResponseDto isVoted (UUID issueId, UUID employeeId) {
        Optional<VoteResponseDto> voteDto = voteRepository.getVote(issueId, employeeId);
        return IsVotedResponseDto.builder()
                .isVoted(voteDto.isPresent())
                .build();
    }

    @Transactional
    public VoteCountResponseDto voteCount (UUID issueId) {
        return voteRepository.getVoteCount(issueId);
    }

    @Transactional
    public void deleteVote (UUID issueId, UUID employeeId) {
        voteRepository.deleteVote(issueId, employeeId);
    }
}
