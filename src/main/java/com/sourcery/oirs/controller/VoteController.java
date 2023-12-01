package com.sourcery.oirs.controller;
import com.sourcery.oirs.dto.response.IsVotedResponseDto;
import com.sourcery.oirs.dto.response.VoteCountResponseDto;
import com.sourcery.oirs.security.CustomUserDetails;
import com.sourcery.oirs.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController{
        private final VoteService voteService;

    @PostMapping("/{issueId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity takeVote(@PathVariable UUID issueId){
        var userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var employeeId = userDetails.getId();
        return voteService.createVote(issueId, employeeId);
    }

    @GetMapping("/{issueId}")
    public IsVotedResponseDto vote(@PathVariable UUID issueId) {
        var userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var employeeId = userDetails.getId();
        return voteService.isVoted(issueId, employeeId);
    }

    @GetMapping("/count/{issueId}")
    public VoteCountResponseDto voteCount(@PathVariable UUID issueId) {
        return voteService.voteCount(issueId);
    }

    @DeleteMapping("/{issueId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVote(@PathVariable UUID issueId) {
        var userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var employeeId = userDetails.getId();
        voteService.deleteVote(issueId, employeeId);
    }
}
