package com.sourcery.oirs.controller;

import com.sourcery.oirs.model.IssueDetailRequestDto;
import com.sourcery.oirs.model.IssueDetailsResponseDto;
import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.service.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/issue")
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;

    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssue();
    }


    @GetMapping("/{id}")
    public IssueDetailsResponseDto getIssueDetails(@PathVariable UUID id){
        return issueService.getIssueDetails(id);
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable(value="id") UUID id){
        issueService.deleteIssue(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateIssue(@PathVariable(value="id") UUID id,
                                            @Valid @RequestBody IssueDetailRequestDto requestDto) {
        issueService.updateIssue(requestDto,id);
        return ResponseEntity.noContent().build(); // Respond with HTTP 204 No Content for a successful update
    }

}
