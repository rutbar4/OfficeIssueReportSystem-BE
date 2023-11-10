package com.sourcery.oirs.controller;

import com.sourcery.oirs.model.IssueDetailsResponseDto;
import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public IssueDetailsResponseDto getIssueDetails(@PathVariable UUID id) {
        return issueService.getIssueDetails(id);
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable(value = "id") UUID id) {
        issueService.deleteIssue(id);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void reportIssue(@RequestBody Issue issue) {
        issueService.reportNewIssue(issue);
    }

    @GetMapping("/open")
    public List<Issue> getOpenIssues() {
        return issueService.getIssuesByStatus("Open");
    }

    @GetMapping("/planned")
    public List<Issue> getPlannedIssues() {
        return issueService.getIssuesByStatus("Pending");
    }

    @GetMapping("/resolved")
    public List<Issue> getResolvedIssues() {
        return issueService.getIssuesByStatus("Resolved");
    }

    @GetMapping("/closed")
    public List<Issue> getClosedIssues() {
        return issueService.getIssuesByStatus("Closed");
    }

    @GetMapping("/reportedBy/{email}")
    public List<Issue> getUserIssues(@PathVariable(value = "email") String email) {
        return issueService.getUserIssues(email);
    }

}