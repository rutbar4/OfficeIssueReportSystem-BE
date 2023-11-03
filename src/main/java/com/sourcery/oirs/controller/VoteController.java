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
    //Get
        //pagal useri pereina vote lenteles pažiūri ar yra issue id kuris get'inamas, jei yra returns true, 
        // jei nera false (arba kažkaip kitaip indentifikuoti kad balsuota ar ne)
    //Post
        //jei iskvieciamas, gauna issueID ir userID tada sukuria komponenta vote lentelej
    //Delete
        //iskvieciamas gauna issueId ir UserID tada istrina toki komponenta Vote lentelej
    @GetMapping("/{issueId}/{userId}")
    public IsVotedResponseDto Vote(@PathVariable UUID issueId, @PathVariable UUID userId) {
        return _voteService.IsVoted(issueId, userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//id
    public VoteResponseDto TakeVote(@Valid @RequestBody VoteRequestDto requestDto){
        //check if that issue and user exist
        //check if the same vote already exists
        return _voteService.CreateVote(requestDto);
    }

//    @DeleteMapping
//    public VoteResponseDto DeleteVote() {
//        _voteService.DeleteVote();
//        return
//    }
}
