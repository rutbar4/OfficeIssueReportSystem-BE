package com.sourcery.oirs.controller;
import com.sourcery.oirs.dto.VoteRequestDto;
import com.sourcery.oirs.dto.response.IsVotedResponseDto;
import com.sourcery.oirs.dto.response.VoteResponseDto;
import com.sourcery.oirs.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController{
        private final VoteService _voteService;
    //Delete
        //iskvieciamas gauna issueId ir UserID tada istrina toki komponenta Vote lentelej

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VoteResponseDto TakeVote(@Valid @RequestBody VoteRequestDto requestDto){
        //check if that issue and user exist
        //check if the same vote already exists
        return _voteService.CreateVote(requestDto);
    }

    @GetMapping("/{issueId}/{userId}")
    public IsVotedResponseDto Vote(@PathVariable UUID issueId, @PathVariable UUID userId) {
        return _voteService.IsVoted(issueId, userId);
    }

    @DeleteMapping("/{issueId}/{userId}")
    public void DeleteVote(@PathVariable UUID issueId, @PathVariable UUID userId) {
        _voteService.DeleteVote(issueId, userId);
    }
}
