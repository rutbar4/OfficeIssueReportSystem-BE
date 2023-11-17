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

    public List<Issue> getAllIssues(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "1") int size) {
        return issueService.getAllIssue(page, size);
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
    public List<Issue> getOpenIssues(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "1") int size) {
        return issueService.getIssuesByStatus("Open", page, size);
    }
    @GetMapping("/open/pageCount")
    public int getOpenCount(){
        return 4;
        //return issueService.getStatusPageCount("Open");
    }
    @GetMapping("/planned")
    public List<Issue> getPlannedIssues(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "1") int size) {
        return issueService.getIssuesByStatus("Pending", page, size);
    }
    @GetMapping("/planned/pageCount")
    public int getPlannedCount(){
        return 3;
        //return issueService.getStatusPageCount("Pending");
    }
    @GetMapping("/resolved")
    public List<Issue> getResolvedIssues(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "1") int size) {
        return issueService.getIssuesByStatus("Resolved", page, size);
    }
    @GetMapping("/resolved/pageCount")
    public int getResolvedCount(){
        return 2;
        //return issueService.getStatusPageCount("Resolved");
    }

    @GetMapping("/closed")
    public List<Issue> getClosedIssues(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "size", defaultValue = "1") int size) {
        return issueService.getIssuesByStatus("Closed", page, size);
    }
    @GetMapping("/closed/pageCount")
    public int getClosedCount(){
        return 6;
        //return issueService.getStatusPageCount("Closed");
    }
    @GetMapping("/reportedBy/{id}")
    public List<Issue> getUserIssues(@PathVariable(value = "id") UUID id, @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "1") int size) {
        return issueService.getUserIssues(id, page, size);
    }
    @GetMapping("/reportedBy/{id}/pageCount")
    public int getUserCount(@PathVariable(value = "id") UUID id){
        return 1;
        //return issueService.getUserPageCount(id);
    }
    @GetMapping("/pageCount")
    public int getPaginationCount(){
        return 8;
        //return issueService.getAllPageCount();
    }

}