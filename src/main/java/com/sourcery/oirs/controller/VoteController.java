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
        //check if that issue and user exist
        var vote = _voteService.CreateVote(requestDto);
        if(vote == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Issue or User does not exist");
        }
        else return ResponseEntity.status(HttpStatus.CREATED).body(vote);
    }

    @GetMapping("/{issueId}/{userId}")
    public IsVotedResponseDto Vote(@PathVariable UUID issueId, @PathVariable UUID userId) {
        return _voteService.IsVoted(issueId, userId);
    }
    @GetMapping("/Count/{issueId}")
    public VoteCountResponseDto VoteCount(@PathVariable UUID issueId) {
        return _voteService.VoteCount(issueId);
    }
    @DeleteMapping("/{issueId}/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteVote(@PathVariable UUID issueId, @PathVariable UUID userId) {
        _voteService.DeleteVote(issueId, userId);
    }

}
