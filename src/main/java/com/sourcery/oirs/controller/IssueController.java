package com.sourcery.oirs.controller;

import com.sourcery.oirs.dto.response.IssueDetailsResponseDto;
import com.sourcery.oirs.model.*;
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
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/issue")
@RequiredArgsConstructor
@Slf4j
public class IssueController {
    private final IssueService issueService;
    private final int defaultPage = IssueControllerConstants.DEFAULT_PAGE;
    private final int defaultPageSize = IssueControllerConstants.DEFAULT_PAGE_SIZE;
    private final String OPEN = IssueControllerConstants.OPEN;
    private final String CLOSED = IssueControllerConstants.CLOSED;
    private final String RESOLVED = IssueControllerConstants.RESOLVED;
    private final String PENDING = IssueControllerConstants.PENDING;


    @GetMapping
    public List<Issue> getAllIssues(@RequestParam(value = "page", defaultValue = "" + defaultPage) int page,
                                    @RequestParam(value = "size", defaultValue = "" + defaultPageSize) int size) {
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
    public List<Issue> getOpenIssues(@RequestParam(value = "page", defaultValue = "" + defaultPage) int page,
                                     @RequestParam(value = "size", defaultValue = "" + defaultPageSize) int size,
                                     @RequestParam(value = "officeID", defaultValue = "") UUID officeID,
                                     @RequestParam(value = "employeeID", defaultValue = "") UUID employeeID) {
        return issueService.getIssuesByStatus(OPEN, page, size, officeID, employeeID);
    }
    @GetMapping("/open/page-count")
    public int getOpenCount(){
        return issueService.getStatusPageCount(OPEN);
    }
    @GetMapping("/planned")
    public List<Issue> getPlannedIssues(@RequestParam(value = "page", defaultValue = "" + defaultPage) int page,
                                        @RequestParam(value = "size", defaultValue = "" + defaultPageSize) int size,
                                        @RequestParam(value = "officeID", defaultValue = "") UUID officeID,
                                        @RequestParam(value = "employeeID", defaultValue = "") UUID employeeID) {
        return issueService.getIssuesByStatus(PENDING, page, size, officeID, employeeID);
    }
    @GetMapping("/planned/page-count")
    public int getPlannedCount(){
        return issueService.getStatusPageCount(PENDING);
    }
    @GetMapping("/resolved")
    public List<Issue> getResolvedIssues(@RequestParam(value = "page", defaultValue = "" + defaultPage) int page,
                                         @RequestParam(value = "size", defaultValue = "" + defaultPageSize) int size,
                                         @RequestParam(value = "officeID", defaultValue = "") UUID officeID,
                                         @RequestParam(value = "employeeID", defaultValue = "") UUID employeeID) {
        return issueService.getIssuesByStatus(RESOLVED, page, size, officeID, employeeID);
    }
    @GetMapping("/resolved/page-count")
    public int getResolvedCount(){
        return issueService.getStatusPageCount(RESOLVED);
    }

    @GetMapping("/closed")
    public List<Issue> getClosedIssues(@RequestParam(value = "page", defaultValue = "" + defaultPage) int page,
                                       @RequestParam(value = "size", defaultValue = "" + defaultPageSize) int size,
                                       @RequestParam(value = "officeID", defaultValue = "") UUID officeID,
                                       @RequestParam(value = "employeeID", defaultValue = "") UUID employeeID) {
        return issueService.getIssuesByStatus(CLOSED, page, size, officeID, employeeID);
    }
    @GetMapping("/closed/page-count")
    public int getClosedCount(){
        return issueService.getStatusPageCount(CLOSED);
    }
    @GetMapping("/reportedBy/{id}")
    public List<Issue> getUserIssues(@PathVariable(value = "id") UUID id, @RequestParam(value = "page", defaultValue = "" + defaultPage) int page,
                                     @RequestParam(value = "size", defaultValue = "" + defaultPageSize) int size) {
        return issueService.getUserIssues(id, page, size);
    }
    @GetMapping("/reportedBy/{id}/page-count")
    public int getUserCount(@PathVariable(value = "id") UUID id){
        return issueService.getUserPageCount(id);
    }
    @GetMapping("/page-count")
    public int getPaginationCount(){
        return issueService.getAllPageCount();
    }
}