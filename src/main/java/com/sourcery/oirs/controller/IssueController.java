package com.sourcery.oirs.controller;

import com.sourcery.oirs.model.IssueDetailsResponseDto;
import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('USER')")
    @PostMapping (value = "/issue")
    @ResponseStatus(HttpStatus.CREATED)
    public void reportIssue (@RequestBody Issue issue){
        issueService.ReportNewIssue(issue);
    }



}
