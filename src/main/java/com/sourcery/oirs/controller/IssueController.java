package com.sourcery.oirs.controller;

import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.model.IssueDetailRequestDto;
import com.sourcery.oirs.model.IssueDetailsResponseDto;
import com.sourcery.oirs.model.OfficeResponseDTO;
import com.sourcery.oirs.service.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/issue")
@RequiredArgsConstructor
@Slf4j
public class IssueController {
    private final IssueService issueService;

    @GetMapping

    public List<Issue> getAllIssues(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        return issueService.getAllIssue(page, size);
    }


    @GetMapping("/{id}")
    public IssueDetailsResponseDto getIssueById(@PathVariable UUID id) {
        return issueService.getIssueById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable(value = "id") UUID id) {
        issueService.deleteIssue(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateIssue(@PathVariable(value="id") UUID id,
                                            @Valid @RequestBody IssueDetailRequestDto requestDto) {
        issueService.updateIssue(requestDto,id);
        return ResponseEntity.noContent().build(); // Respond with HTTP 204 No Content for a successful update
    }
    @GetMapping("/offices")
    public List<OfficeResponseDTO> getAllOffices() {
        return issueService.getAllOffices();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void reportIssue(@RequestBody Issue issue) {
        issueService.reportNewIssue(issue);
    }

    @GetMapping("/open")
    public List<Issue> getOpenIssues(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        return issueService.getIssuesByStatus("Open", page, size);
    }
    @GetMapping("/open/pageCount")
    public int getOpenCount(){
        return issueService.getStatusPageCount("Open");
    }
    @GetMapping("/planned")
    public List<Issue> getPlannedIssues(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        return issueService.getIssuesByStatus("Pending", page, size);
    }
    @GetMapping("/planned/pageCount")
    public int getPlannedCount(){
        return issueService.getStatusPageCount("Pending");
    }
    @GetMapping("/resolved")
    public List<Issue> getResolvedIssues(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return issueService.getIssuesByStatus("Resolved", page, size);
    }
    @GetMapping("/resolved/pageCount")
    public int getResolvedCount(){
        return issueService.getStatusPageCount("Resolved");
    }

    @GetMapping("/closed")
    public List<Issue> getClosedIssues(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        return issueService.getIssuesByStatus("Closed", page, size);
    }
    @GetMapping("/closed/pageCount")
    public int getClosedCount(){
        return issueService.getStatusPageCount("Closed");
    }
    @GetMapping("/reportedBy/{id}")
    public List<Issue> getUserIssues(@PathVariable(value = "id") UUID id, @RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        return issueService.getUserIssues(id, page, size);
    }
    @GetMapping("/reportedBy/{id}/pageCount")
    public int getUserCount(@PathVariable(value = "id") UUID id){
        return issueService.getUserPageCount(id);
    }
    @GetMapping("/pageCount")
    public int getPaginationCount(){
        return issueService.getAllPageCount();
    }


}