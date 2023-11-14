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
        private final VoteService _voteService;

    @PostMapping("/{issueId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity TakeVote(@PathVariable UUID issueId){
        var userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var employeeId = userDetails.getId();
        var vote = _voteService.CreateVote(issueId, employeeId);
        if(vote == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("issue or employee does not exist");
        }
        else return ResponseEntity.status(HttpStatus.CREATED).body(vote);
    }

    @GetMapping("/{issueId}")
    public IsVotedResponseDto Vote(@PathVariable UUID issueId) {
        var userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var employeeId = userDetails.getId();
        return _voteService.IsVoted(issueId, employeeId);
    }

    @GetMapping("/count/{issueId}")
    public VoteCountResponseDto VoteCount(@PathVariable UUID issueId) {
        return _voteService.VoteCount(issueId);
    }

    @DeleteMapping("/{issueId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteVote(@PathVariable UUID issueId) {
        var userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var employeeId = userDetails.getId();
        _voteService.DeleteVote(issueId, employeeId);
    }
}
