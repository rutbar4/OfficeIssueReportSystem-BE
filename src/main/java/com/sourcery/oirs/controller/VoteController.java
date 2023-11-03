package com.sourcery.oirs.controller;
import com.sourcery.oirs.dto.VoteRequestDto;
import com.sourcery.oirs.dto.response.IsVotedResponseDto;
import com.sourcery.oirs.dto.response.VoteCountResponseDto;
import com.sourcery.oirs.dto.response.VoteResponseDto;
import com.sourcery.oirs.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController{
        private final VoteService _voteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity TakeVote(@Valid @RequestBody VoteRequestDto requestDto){
        var vote = _voteService.CreateVote(requestDto);
        if(vote == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Issue or Employee does not exist");
        }
        else return ResponseEntity.status(HttpStatus.CREATED).body(vote);
    }

    @GetMapping("/{issueId}/{employeeId}")
    public IsVotedResponseDto Vote(@PathVariable UUID issueId, @PathVariable UUID employeeId) {
        return _voteService.IsVoted(issueId, employeeId);
    }
    @GetMapping("/Count/{issueId}")
    public VoteCountResponseDto VoteCount(@PathVariable UUID issueId) {
        return _voteService.VoteCount(issueId);
    }
    @DeleteMapping("/{issueId}/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteVote(@PathVariable UUID issueId, @PathVariable UUID employeeId) {
        _voteService.DeleteVote(issueId, employeeId);
    }

}
